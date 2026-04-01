package out.production.day01.com.zrl.day04.arrDomes;

import java.util.Scanner;

public class dome03 {
    public static void main(String[] args) {
        int arr [] = {33,5,22,44,55,33};
        Scanner sc = new Scanner(System.in);
        System.out.println("请录入一个整数：");
        int number = sc.nextInt();
        int a = 0;
        for (int i = 0; i < arr.length; i++) {
            if(number == arr[i]) {
                System.out.println(arr[i]);

                break;
            }
            a = a + 1;
        }
        if (a == 6) System.out.println("数据在数组中不存在");


    }


}
