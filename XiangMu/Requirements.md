# 用户管理系统 - 项目需求文档

## 项目概述
基于 Spring Boot + MyBatis-Plus + MySQL 的用户管理系统，实现用户注册、登录、权限管理及用户信息的增删改查功能。

## 技术栈
- **后端框架：** Spring Boot 3.x
- **ORM框架：** MyBatis-Plus 3.5.x
- **数据库：** MySQL 8.0
- **构建工具：** Maven
- **JDK版本：** JDK 17
- **其他：** JWT（登录鉴权）、Lombok（简化代码）
- **开发工具：** IntelliJ IDEA

## 数据库设计

### 用户表（user）
| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | BIGINT | 主键，自增 |
| username | VARCHAR(50) | 用户名，唯一 |
| password | VARCHAR(100) | 密码（BCrypt加密存储） |
| nickname | VARCHAR(50) | 昵称 |
| email | VARCHAR(100) | 邮箱 |
| phone | VARCHAR(20) | 手机号 |
| role | VARCHAR(20) | 角色（USER/ADMIN），默认USER |
| status | TINYINT | 状态（0禁用/1启用），默认1 |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |

## 功能模块

### 模块一：用户注册
- 接口：POST /api/user/register
- 功能：新用户注册
- 参数：username、password、nickname、email、phone
- 逻辑：
  1. 校验用户名是否已存在
  2. 校验参数格式（用户名长度、邮箱格式等）
  3. 密码BCrypt加密后存储
  4. 返回注册成功信息

### 模块二：用户登录
- 接口：POST /api/user/login
- 功能：用户登录，返回JWT Token
- 参数：username、password
- 逻辑：
  1. 根据用户名查询用户
  2. 校验密码是否正确
  3. 生成JWT Token返回
  4. 后续请求携带Token访问需鉴权的接口

### 模块三：用户信息管理（CRUD）
- 查询用户列表：GET /api/user/list
  - 支持分页（pageNum, pageSize）
  - 支持按用户名/昵称模糊搜索
  - 返回分页数据
- 查询单个用户：GET /api/user/{id}
- 更新用户信息：PUT /api/user/{id}
  - 可修改昵称、邮箱、手机号
  - 不能修改用户名
- 删除用户：DELETE /api/user/{id}
  - 仅管理员可操作

### 模块四：权限控制
- 普通用户：只能查看和修改自己的信息
- 管理员：可以查看、修改、删除所有用户
- 通过自定义注解 + AOP实现权限校验

## 统一返回格式
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {}
}
```

## 项目目录结构
```
user-management/
├── src/main/java/com/example/usermanagement/
│   ├── controller/          # 控制器层
│   │   └── UserController.java
│   ├── service/             # 服务层
│   │   ├── UserService.java
│   │   └── impl/
│   │       └── UserServiceImpl.java
│   ├── mapper/              # 数据访问层
│   │   └── UserMapper.java
│   ├── entity/              # 实体类
│   │   └── User.java
│   ├── dto/                 # 数据传输对象
│   │   ├── UserRegisterDTO.java
│   │   ├── UserLoginDTO.java
│   │   └── UserUpdateDTO.java
│   ├── vo/                  # 返回视图对象
│   │   └── UserVO.java
│   ├── common/              # 公共类
│   │   ├── Result.java      # 统一返回结果
│   │   └── ResultCode.java  # 返回状态码
│   ├── config/              # 配置类
│   │   ├── MybatisPlusConfig.java
│   │   └── WebConfig.java
│   ├── interceptor/         # 拦截器
│   │   └── LoginInterceptor.java
│   ├── util/                # 工具类
│   │   └── JwtUtil.java
│   ├── annotation/          # 自定义注解
│   │   └── RequireLogin.java
│   └── exception/           # 异常处理
│       ├── BusinessException.java
│       └── GlobalExceptionHandler.java
├── src/main/resources/
│   ├── application.yml      # 配置文件
│   └── mapper/              # MyBatis XML（如需要）
├── pom.xml
└── README.md
```

## 开发顺序（严格按照这个顺序来）

### 第1天：项目搭建
1. 使用Spring Initializr创建项目（选Spring Web、MySQL Driver）
2. 配置pom.xml，引入MyBatis-Plus、JWT、Lombok依赖
3. 配置application.yml（数据库连接、端口等）
4. 创建项目目录结构

### 第2天：数据库 + 实体类
1. 在MySQL中创建数据库和user表
2. 编写User实体类（@TableName、@TableId等注解）
3. 编写UserMapper接口（继承BaseMapper）
4. 测试：能通过Mapper查询到数据库数据

### 第3天：统一返回 + 异常处理
1. 编写Result统一返回类
2. 编写ResultCode状态码枚举
3. 编写BusinessException自定义异常
4. 编写GlobalExceptionHandler全局异常处理

### 第4天：注册功能
1. 编写UserRegisterDTO（接收注册参数）
2. 编写UserService接口和实现类
3. 实现注册逻辑（参数校验、用户名查重、密码加密）
4. 编写UserController，开放注册接口
5. 用Postman测试注册功能

### 第5天：登录 + JWT
1. 引入JWT依赖（jjwt）
2. 编写JwtUtil工具类（生成Token、解析Token）
3. 实现登录逻辑（验证用户名密码、返回Token）
4. 用Postman测试登录，拿到Token

### 第6天：登录拦截器
1. 编写LoginInterceptor拦截器
2. 从请求头获取Token并验证
3. 配置WebConfig，设置拦截路径和排除路径
4. 测试：不带Token访问被拦截，带Token正常访问

### 第7天：查询功能
1. 编写UserVO（返回给前端的用户信息，不含密码）
2. 实现查询单个用户接口
3. 实现分页查询（MyBatis-Plus分页插件）
4. 实现按用户名/昵称模糊搜索
5. 用Postman测试

### 第8天：修改 + 删除功能
1. 编写UserUpdateDTO
2. 实现更新用户信息接口
3. 实现删除用户接口（仅管理员）
4. 权限校验：普通用户只能改自己，管理员能改所有人
5. 用Postman测试

### 第9天：完善 + 测试
1. 自定义@RequireLogin注解 + AOP实现权限校验
2. 完善参数校验（@Valid注解）
3. 全流程测试：注册→登录→查询→修改→删除
4. 修bug

### 第10天：部署
1. 项目打包（mvn package）
2. 购买/使用云服务器（或用免费的Render）
3. 安装MySQL、JDK环境
4. 部署项目到服务器
5. 用Postman远程测试

## 依赖清单（pom.xml核心依赖）
```xml
<!-- Spring Boot Web -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- MyBatis-Plus -->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-spring-boot3-starter</artifactId>
    <version>3.5.5</version>
</dependency>

<!-- MySQL Driver -->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>

<!-- Lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>

<!-- JWT -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.12.5</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.12.5</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.12.5</version>
    <scope>runtime</scope>
</dependency>
```

## 接口汇总

| 接口 | 方法 | 路径 | 是否需要登录 | 说明 |
|------|------|------|-------------|------|
| 注册 | POST | /api/user/register | 否 | 新用户注册 |
| 登录 | POST | /api/user/login | 否 | 登录获取Token |
| 用户列表 | GET | /api/user/list | 是 | 分页+搜索 |
| 用户详情 | GET | /api/user/{id} | 是 | 查询单个用户 |
| 更新用户 | PUT | /api/user/{id} | 是 | 修改用户信息 |
| 删除用户 | DELETE | /api/user/{id} | 是+管理员 | 删除用户 |

## 验收标准
- [ ] 能成功注册用户，密码加密存储
- [ ] 能成功登录，返回有效JWT Token
- [ ] 带Token能访问需鉴权接口，不带Token被拦截
- [ ] 分页查询正常工作
- [ ] 模糊搜索正常工作
- [ ] 普通用户只能修改自己的信息
- [ ] 管理员能管理所有用户
- [ ] 项目能打包部署到服务器运行
- [ ] 代码推送到GitHub

---

直接复制上面全部内容，发给AI，然后跟它说：

> "请按照这份文档，从第1天开始，手把手带我做这个项目。每一步都给我完整代码，我复制粘贴就能跑的那种。遇到报错我贴给你，你帮我解决。"