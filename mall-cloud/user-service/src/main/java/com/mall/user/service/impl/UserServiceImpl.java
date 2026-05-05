package com.mall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.result.Result;
import com.mall.user.entity.User;
import com.mall.user.mapper.UserMapper;
import com.mall.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * 用户业务逻辑实现
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final StringRedisTemplate redisTemplate;

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule())
            .disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    private static final String USER_CACHE_PREFIX = "cache:user:";
    private static final long CACHE_TTL_SECONDS = 3600;

    // ========== 查询（带缓存） ==========
    @Override
    public Result<User> getById(Long id) {
        // 1. 先查 Redis 缓存
        String cacheKey = USER_CACHE_PREFIX + id;
        String cachedJson = redisTemplate.opsForValue().get(cacheKey);

        if (cachedJson != null) {
            // 空值标记：表示这个 ID 之前查过，数据库里也不存在
            if ("NULL".equals(cachedJson)) {
                return Result.notFound("用户不存在");
            }
            // 正常缓存命中
            try {
                User user = objectMapper.readValue(cachedJson, User.class);
                return Result.ok(user);
            } catch (Exception e) {
                log.error("读缓存失败", e);
                redisTemplate.delete(cacheKey);
            }
        }

        // 2. 缓存没命中，用互斥锁查数据库（防缓存击穿）
        User user = queryWithMutex(id);    // ← 改成这个
        if (user == null) {
            redisTemplate.opsForValue().set(cacheKey, "NULL", 60, TimeUnit.SECONDS);
            return Result.notFound("用户不存在");
        }

        // 3. 写回缓存（先洗掉密码再序列化，安全！）
        try {
            user.setPassword(null);
            String json = objectMapper.writeValueAsString(user);
            redisTemplate.opsForValue().set(cacheKey, json, CACHE_TTL_SECONDS, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("写缓存失败", e);
        }

        // 4. 洗掉密码返回
        user.setPassword(null);
        return Result.ok(user);
    }

    // ========== 新增 ==========
    @Override
    public Result<User> create(User user) {
        Long count = userMapper.selectCount(
                new LambdaQueryWrapper<User>()
                        .eq(User::getUsername, user.getUsername())
        );
        if (count > 0) {
            return Result.badRequest("用户名已存在");
        }

        String encryptedPassword = DigestUtils.md5DigestAsHex(
                user.getPassword().getBytes(StandardCharsets.UTF_8)
        );
        user.setPassword(encryptedPassword);

        userMapper.insert(user);

        user.setPassword(null);
        return Result.ok(user);
    }

    // ========== 更新 ==========
    @Override
    public Result<User> update(User user) {
        User dbUser = userMapper.selectById(user.getId());
        if (dbUser == null) {
            return Result.notFound("用户不存在");
        }

        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(
                    DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8))
            );
        } else {
            user.setPassword(null);
        }

        userMapper.updateById(user);

        // 更新后删缓存
        redisTemplate.delete(USER_CACHE_PREFIX + user.getId());

        return getById(user.getId());
    }

    // ========== 删除 ==========
    @Override
    public Result<Void> delete(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return Result.notFound("用户不存在");
        }
        userMapper.deleteById(id);

        // 删除后清缓存
        redisTemplate.delete(USER_CACHE_PREFIX + id);

        return Result.ok();
    }

    /**
     * 尝试获取分布式锁 — 使用 Redis SETNX + 过期时间
     *
     * @param key  锁的 Key（例如 lock:user:1）
     * @param ttl  锁过期时间（防止死锁）
     * @return true = 获得锁，false = 别人持有锁
     */
    private boolean tryLock(String key, long ttl) {
        // SET key 1 NX EX ttl：
        //   NX = Not eXists，只有 key 不存在时才创建
        //   EX = 设置过期时间
        // 返回值 true 表示创建成功（获得锁），false 表示 key 已存在（别人持有锁）
        Boolean success = redisTemplate.opsForValue()
                .setIfAbsent("lock:" + key, "1", ttl, TimeUnit.SECONDS);
        return Boolean.TRUE.equals(success);
    }

    /**
     * 释放锁 — 删除 Redis 里的锁 Key
     */
    private void unlock(String key) {
        redisTemplate.delete("lock:" + key);
    }

    /**
     * 带互斥锁的数据库查询 — 防缓存击穿
     *
     * @param id 用户 ID
     * @return 用户对象（可能为 null），直接返回 null 让调用方处理
     */
    private User queryWithMutex(Long id) {
        // 抢分布式锁（最多等 10 秒）
        String lockKey = USER_CACHE_PREFIX + id;
        long maxWaitSeconds = 10;
        long start = System.currentTimeMillis();

        while (System.currentTimeMillis() - start < maxWaitSeconds * 1000) {
            // 尝试获取锁
            if (tryLock(lockKey, 30)) {   // 锁 30 秒自动过期，防死锁
                try {
                    // 成功获得锁 → 读数据库并回写缓存
                    User user = userMapper.selectById(id);
                    if (user != null) {
                        try {
                            user.setPassword(null);
                            String json = objectMapper.writeValueAsString(user);
                            redisTemplate.opsForValue().set(
                                    USER_CACHE_PREFIX + id, json,
                                    CACHE_TTL_SECONDS, TimeUnit.SECONDS);
                        } catch (Exception e) {
                            log.error("写缓存失败", e);
                        }
                    }
                    return user;
                } finally {
                    unlock(lockKey);  // 无论如何都要释放锁
                }
            }

            // 没抢到锁，短暂休眠后重试
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        return null;  // 超时也没抢到锁，直接返回 null
    }

}