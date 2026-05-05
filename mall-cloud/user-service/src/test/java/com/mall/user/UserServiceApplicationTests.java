package com.mall.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Map;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void testConnection() {
		// 查一条数据，看看能不能连上数据库
		Map<String, Object> user = jdbcTemplate.queryForMap(
				"SELECT id, username, phone FROM t_user WHERE username = ?",
				"zhangsan"
		);
		System.out.println("查询结果：" + user);
		// 打印出来应该看到：{id=1, username=zhangsan, phone=13800138000}
	}
}