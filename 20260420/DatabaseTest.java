import java.sql.*;

public class DatabaseTest {
    public static void main(String[] args) {
        // 1. 数据库连接信息（请确保地址和密码正确）
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8mb4";
        String user = "root";
        String password = "root"; // 这里填你登录 Navicat 用的密码

        // 2. 编写 SQL 模板
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // --- 练习环节：在这里填入你的代码 ---
            // 提示：用 pstmt.setInt 设置 id，然后用执行方法执行
            pstmt.setInt(1, 1);
            int row1 = pstmt.executeUpdate();
            System.out.println("第一次删除结果：" + row1);

            pstmt.setInt(1, 2);
            int row2 = pstmt.executeUpdate();
            System.out.println("第一次删除结果：" + row2);

            System.out.println("操作完成！");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}