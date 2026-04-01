package Arr;

import java.util.Scanner;

public class ArrTest02 {
    public static void main(String[] args) {
        int [] nums = {1,3,5,6};
        int a = 0 ;

        Scanner target = new Scanner(System.in);
        System.out.println("请输入一个数字：");
        int targetNum = target.nextInt();

        for (int i = 0; i < nums.length; i++) {
            if(targetNum == nums[i]){
                break;
            }
            a++;
        }
        if(a == 4) {
            for (int j = 0; j < nums.length; j++) {
                if (targetNum <= nums[j]) {
                    a = j;
                    break;
                }
            }
        }
        System.out.println(a);
    }
}
