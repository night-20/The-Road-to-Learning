package FirstDay;

import java.util.Scanner;

/**
 * 任务：定义一个用户类
 * 知识点：类、属性、构造方法、toString方法
 */


public class UserDemo {



    public static void main(String[] args) {
        // 创建对象（实例化）
        User u1 = new User(1, "张三", "zhangsan@example.com",123456);
        User u2 = new User(2, "李四", "lisi@example.com",123456);

        // 调用方法
        u1.printInfo();
        u2.printInfo();



    }

}