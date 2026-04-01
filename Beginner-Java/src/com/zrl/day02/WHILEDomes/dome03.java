package com.zrl.day02.WHILEDomes;

public class dome03 {
    public static void main(String[] args) {
        double zhight = 8848860.0;
        double phight = 0.1;
        int a = 0;
        while (phight < zhight){
            phight = phight * 2;
            a++;
        }
        System.out.println(a + "次");
    }
}
