package com.zrl.day02.WHILEDomes;

import java.util.Scanner;

public class dome04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入数字：");
        int num = sc.nextInt();
        if (num < 0) num = -num;
        int b= 0;
        while (num != 0){
            num = num / 10;
            b = num % 10;
        }
        System.out.println(b);
    }
}
