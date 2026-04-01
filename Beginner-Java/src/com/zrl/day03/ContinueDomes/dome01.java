package com.zrl.day03.ContinueDomes;

public class dome01 {
    public static void main(String[] args) {
        for(int a = 1;a <= 100;a++){
            if (a == 7 || a % 10 == 7) {
                System.out.println(a + "过");
                continue;
            }
            if (a % 7 == 0) {
                System.out.println(a + "过");
                continue;
            }

        }

    }
}
