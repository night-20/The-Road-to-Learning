package com.zrl.day01.FORDomes;

public class dome02 {
    public static void main(String[] args) {
        int b = 0;
        for(int a = 1 ; a <= 100 ; a++){
            if (a % 2 == 0){
                b = b + a;
            }
        }
        System.out.println(b);
    }
}
