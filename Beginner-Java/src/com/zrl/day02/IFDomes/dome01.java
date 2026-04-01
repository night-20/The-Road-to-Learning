package com.zrl.day02.IFDomes;

import java.util.Scanner;

public class dome01 {
    public static void main(String[] args) {
        int food;
        double bao;
        double mei = 0.0;
        Scanner sc = new Scanner(System.in);
        System.out.println("烧烤价格：");
        food = sc.nextInt();
        bao = food * 0.9;
        if(food >= 30){
            mei = (int)(food - 10);
        }
        if (bao > mei) {
            System.out.println("美单App更划算");
        }else {System.out.println("饱了么更划算");}
    }
}
