package com.zrl.day01.FORDomes;

import java.util.Scanner;

public class dome03 {
    public static void main(String[] args) {
        int c = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入两个数字");
        int a = sc.nextInt();
        int b = sc.nextInt();
        for(int d = 0; a<=b;a++)
            if (a % 3 == 0 && a % 5 == 0) c++;
        System.out.println("既能被3整除，又能被5整除数字有" + c);
    }
}
