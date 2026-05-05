针对 MyBatis-Plus (MP)，你只需要掌握以下 4 个关键环节 就能上手干活了：

1. 映射（Mapping） 🔗
这是第一步，解决“Java 对象”和“数据库表”怎么对应的问题。

重点：学会使用 @TableName、@TableId、@TableField 这三个注解。

2. 基础增删改查（BaseMapper） 🛠️
这是 MP 的核心“外挂”。

重点：让你的 Mapper 接口继承 BaseMapper<T>。一旦继承，你就能直接调用 insert、deleteById、updateById、selectList 等方法，一行 SQL 都不用写。

3. 条件构造器（Wrapper） 🔍
这是最常用的功能。当你要做“查询年龄大于 18 岁且名字里有‘张’的人”这种复杂查询时，需要用到它。

重点：掌握 QueryWrapper 和 LambdaQueryWrapper（推荐后者，更安全）。学会使用 .eq() (等于)、.gt() (大于)、.like() (模糊查询) 等链式调用。

4. 分页插件（Pagination） 📄
实际项目中几乎都要分页。

重点：在配置类里添加一个 MybatisPlusInterceptor 插件，然后使用 Page 对象进行查询。