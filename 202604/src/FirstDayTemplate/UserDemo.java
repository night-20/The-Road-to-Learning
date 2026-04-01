package FirstDay;

import java.util.Scanner;

/**
 * 任务：定义一个用户类
 * 优化点：
 * 1. 职责分离：类只负责逻辑，不负责交互
 * 2. 类型规范：密码使用 String
 * 3. 逻辑闭环：在 main 方法中处理循环尝试
 */


public class UserDemo {
    public static void main(String[] args) {
        // 1. 初始化数据
        User u1 = new User(1, "张三", "zhangsan@example.com", "123456");
        u1.printInfo();

        // 2. 模拟登录逻辑
        Scanner sc = new Scanner(System.in);
        int attempts = 0;

        while (attempts < 3) {
            System.out.print("请输入密码（剩余尝试次数：" + (3 - attempts) + "）: ");
            String input = sc.next();

            if (u1.checkPassword(input)) {
                System.out.println("登录成功！欢迎 " + u1.getUsername());
                break;
            } else {
                attempts++;
                if (attempts < 3) {
                    System.out.println("密码错误，请再试一次。");
                } else {
                    System.out.println("账号已锁定，请联系管理员。");
                }
            }
        }
        sc.close();
    }
}