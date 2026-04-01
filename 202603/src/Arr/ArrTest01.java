package Arr;

public class ArrTest01 {
    public static void main(String[] args) {
        int [] arr1 = {1,3,5,7,9};
        int [] arr2 = {2,4,6,8,10};
        int [] arr3 = new int[10];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < arr1.length && j < arr2.length){
            if(arr1[i] < arr2[j]){
                arr3[k] = arr1[i];
                i++;
            }else {
                arr3[k] = arr2[j];
                j++;
            }k++;
        }

        while (i < arr1.length) {
            arr3[k] = arr1[i];
            i++;
            k++;
        }

        while (j < arr2.length) {
            arr3[k] = arr2[j];
            j++;
            k++;
        }

        for (int a = 0; a < arr3.length; a++) {
            System.out.println(arr3[a]);
        }
    }
}
