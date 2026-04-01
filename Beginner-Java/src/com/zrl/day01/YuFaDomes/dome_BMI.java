package com.zrl.day01.YuFaDomes;

public class dome_BMI {
    public static void main(String[] args) {
    /*    //BMI = 体重（kg）/身高^2（m）

        double weigth = 0;
        double height = 0;
        double bmi = 0;

        System.out.println("请输入体重（kg）：");
        Scanner sc = new Scanner(System.in);
        weigth = sc.nextDouble();
        System.out.println("请输入身高（m）：");
        height = sc.nextDouble();
        bmi = weigth/(height*height);
        System.out.println("BMI="+bmi);
        if(bmi<18.5){
            System.out.println("过轻");
        }else if(bmi<25){
            System.out.println("正常");
        }else if(bmi<28){
            System.out.println("过重");
            System.out.println("肥胖");
        }

    }*/

        //计算出你当前的身高，在标准的BMI下，最多是多少千克？
        double height = 1.73;
        double bmi = 23.9;

        double weight;
        weight = bmi*height*height;
        System.out.println(weight);





    }
}
