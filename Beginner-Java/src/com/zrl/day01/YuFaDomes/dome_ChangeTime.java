package com.zrl.day01.YuFaDomes;

import java.util.Scanner;

public class dome_ChangeTime {
    public static void main(String[] args) {
        int seconds,hours,mins;
        Scanner sc = new Scanner(System.in);
        seconds = sc.nextInt();
        mins = seconds%3600/60;
        hours = seconds/3600;
        seconds = seconds%3600%60;

        System.out.println(seconds);
        System.out.println(mins);
        System.out.println(hours);
    }
}
