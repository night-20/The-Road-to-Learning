package com.zrl.day01.FORDomes;

import java.util.Scanner;

public class dome05 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("要求前几项的和：");
        int n = sc.nextInt();
        int S = 0;
        int b = 1;
        for(int a = 1 ; a <= n ; a++ ){
            if(a % 2 == 0) {
                b=-a;
                S = S + b;
            }else {
                S = S + a;
            }
        }
        System.out.println(S);
    }
}
