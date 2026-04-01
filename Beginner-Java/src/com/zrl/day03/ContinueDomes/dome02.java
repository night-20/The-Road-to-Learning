package com.zrl.day03.ContinueDomes;

import java.util.Scanner;

public class dome02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
            for(int a = 1;a <= num;a++){
                if (a == 4 || a % 10 == 4 || a % 4 == 0) {
                continue;
            }
        System.out.println(a);
        }
    }
}
