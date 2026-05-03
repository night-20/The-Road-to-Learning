import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        School school = new School();
        int score = 0;
        ArrayList<String> list = school.getStudent();

        while (true) {
            System.out.println("--- 学生管理系统 ---");
            System.out.println("1. 添加学生");
            System.out.println("2. 查看所有学生");
            System.out.println("3. 删除学生");
            System.out.println("4. 修改学生姓名");
            System.out.println("5. 退出");
            System.out.print("请选择操作：");

            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.println("请输入学生姓名:");
                String name = sc.next();
                school.addStudent(name);
                System.out.println("已添加学生：" + school.getStudent());
                score++;

            } else if (choice == 2) {
                if (list.size() == 0) {
                    System.out.println("没有学生无法查询");
                    continue;
                } else {
                    System.out.println("以下是所有学生姓名名单");
                    System.out.println(school.getStudent());

                }
            } else if (choice == 3) {
                if (list.size() == 0) {
                    System.out.println("没有学生无法删除");
                    continue;
                } else {
                    System.out.println("以下是所有学生名单");
                    System.out.println(school.getStudent());
                    System.out.println("请输入你需要删除的学生姓名的序号：");
                    int number = sc.nextInt();
                    if (number > 0 && number < list.size()) {
                        school.remove(number);
                    } else {
                        System.out.println("输入错误请重新输入");
                    }
                }
            } else if (choice == 4) {
                if (list.size() == 0) {
                    System.out.println("没有学生无法修改");
                    continue;
                } else {
                    System.out.println("以下是所有学生名单");
                    System.out.println(school.getStudent());
                    System.out.println("请输入你需要修改的学生姓名的序号：");
                    int number = sc.nextInt();
                    if (number > 0 && number < list.size()) {
                        System.out.println("请输入你需要修改后的姓名：");
                        String newName = sc.next();
                        school.updateStudent(number,newName);
                    } else {
                        System.out.println("输入错误请重新输入");
                    }
                }
            } else if (choice == 5) {
                System.out.println("系统已退出。");
                break; // 跳出循环，结束程序
            }
        }

    }
}
