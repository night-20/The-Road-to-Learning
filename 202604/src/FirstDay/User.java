package FirstDay;

import java.util.Scanner;

class User {
    // 1. 属性（成员变量）：描述对象的状态
    private Integer id;
    private String username;
    private String email;
    private int passwd;

    // 2. 构造方法：用于创建对象时初始化数据
    public User(Integer id, String username, String email, int passwd) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwd= passwd;
    }

    // 3. 行为（方法）：描述对象的功能
    public void printInfo() {
        System.out.println("用户ID: " + id + ", 用户名: " + username + ", 邮箱: " + email + ",密码: " + passwd);
    }

    public boolean checkPassword(String input){
        Scanner sc = new Scanner(System.in);
        System.out.println("需要确认你的密码：");
        int a = sc.nextInt();
        if(a == this.passwd){
            System.out.println("true");
        }else {
            System.out.printf("请再来一次:");

        }
        return false;
    }

    // Getter/Setter: 以后 SpringBoot 开发必须用到
    public String getUsername() {
        return username;
    }
}
