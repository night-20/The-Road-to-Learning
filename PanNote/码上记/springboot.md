#### Springboot分层结构
```
Controller  ←  接收请求，返回结果（相当于"前台"）
    ↓
Service     ←  处理业务逻辑（相当于"后厨"）
    ↓
Mapper      ←  操作数据库（相当于"仓库"）
    ↓
数据库
```


```
com.memos.memosapp
├── controller      ← 接口层
├── service         ← 业务层
├── mapper          ← 数据库层
├── entity          ← 实体类（对应数据库表）
└── MemosAppApplication.java
```

#### 先理解 GET 和 POST 的区别

```
||GET|POST|
|---|---|---|
|用途|查询数据|提交数据（注册、发文章等）|
|参数位置|在 URL 里 `/hello/张三`|在请求体里（看不见）|
|浏览器能直接测吗|✅ 能|❌ 不能，要用 Postman|
```


#### 理解 Spring Boot 的启动原理

打开 `MemosAppApplication.java`，你会看到：

java

```java
@SpringBootApplication
public class MemosAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemosAppApplication.class, args);
    }
}
```

这 3 行代码背后做了什么：

```
main() 执行
    ↓
SpringApplication.run() 启动
    ↓
① 扫描所有 @RestController / @Service 等注解的类，注册进容器
② 读取 application.yml，连接数据库
③ 启动内嵌的 Tomcat 服务器，监听 8080 端口
    ↓
项目启动完成，开始接收请求
```

> 💡 `@SpringBootApplication` 是个组合注解，相当于同时加了 3 个注解，你现在不用深究，记住"它让 Spring Boot 跑起来"就够了。

