package out.production.day01.com.zrl.day04.arrDomes;

import java.util.Random;

public class dome06 {
    public static void main(String[] args) {
        int[] arr = new int[10];
        Random ran = new Random();

        for (int i = 0; i < arr.length; ) {
            int num = ran.nextInt(1, 101);
            int a = 0;
            for (int j = 0; j < arr.length; j++) {
                if (num == arr[j]) {
                    a++;
                    break;
                }
            }
            if (a == 0) {
                arr[i] = num;
                i++;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
