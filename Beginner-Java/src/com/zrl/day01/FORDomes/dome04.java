package com.zrl.day01.FORDomes;

public class dome04 {
    public static void main(String[] args) {
    int a = 0;
    int b = 1;
    int c = 0;
        for(int d = 1;d <= 8;d++){
            c = a + b ;
            a = b;
            b = c;
            // 第一次 c=0+1+0 b=1
            // 第二次 c=0+1+1 b=2
            // 第三次 c=0+2+2
        }
        System.out.println(c);
    }
}
