package com.zrl.day01.YuFaDomes;

import java.util.Scanner;

public class dome_SanWeiShu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int a,b,c;
        c=num%10;
        b=(num/10)%10;
        a=num/100;
        System.out.println(c);
        System.out.println(b);
        System.out.println(a);
    }
}
