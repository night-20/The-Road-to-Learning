✅ 今天掌握的核心知识点：

- Maven 父子工程结构：
  父工程 <packaging>pom</packaging>，统一管版本和子模块；
  子模块 <parent> 指向父工程，继承统一配置。

- 统一返回结果 Result<T>：
  所有接口返回相同 JSON 结构 {code, message, data}，
  前端不用每个接口重新适配，后端统一异常处理也能用。

- MyBatis-Plus 代码生成：
  BaseMapper<T> 继承即拥有 CRUD，不用写一行 SQL；
  LambdaQueryWrapper 类型安全地构造查询条件；
  @TableLogic 自动把 delete 变成逻辑删除 UPDATE。

- Service 层分层：
  Controller → Service 接口 → ServiceImpl 实现 → Mapper。
  接口解耦，方便后面加缓存、切面。

- 密码安全：
  永远不在接口返回密码字段（setPassword(null)）；
  存储时必须加密（MD5/BCrypt），绝不能明文入库。

🎯 明天预告：
Day 2 — Redis 缓存。把用户查询结果缓存到 Redis，
学分布式锁解决缓存击穿，学缓存穿透/雪崩/击穿的面试八股文。

💡 面试可能问的问题：
1. 为什么 Controller 返回 Result 而不是直接返回 User？
   答：统一返回格式，前端能统一处理成功/失败，全局异常处理器也能
   用同一个格式返回错误，不用在每个接口里 try-catch。

2. 什么是逻辑删除？为什么用逻辑删除而不是物理删除？
   答：删除时只改删除标记字段（deleted=1），不真的删数据。
   好处：数据可恢复、保留操作日志、不破坏外键关联。
   MyBatis-Plus 通过 @TableLogic 自动给所有 SQL 拼上 WHERE deleted=0。