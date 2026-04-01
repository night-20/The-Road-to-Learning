package com.zrl.day02.IFDomes;

public class dome02 {
    public static void main(String[] args) {
        int pay = 0,card;
        if (pay < 0) {
            System.out.println("异常");
        }else if (pay >= 1000 && pay < 1999) {
            card =pay + 200;
            System.out.println("卡内余额为：" + card);
        } else if (pay < 2999) {
            card = pay + 500;
            System.out.println("卡内余额为：" + card);
        }else if (pay < 4999){
            card = pay + 700;
            System.out.println("卡内余额为：" + card);
        } else if (pay < 9999) {
            card = pay + 1300;
            System.out.println("卡内余额为：" + card);
        }
    }
}
