package out.production.day01.com.zrl.day04.arrDomes;

public class dome07 {
    public static void main(String[] args) {
        int [] arr = {1,1,2,2,2,2,3,3,3,3};

        int slow = 0;
        int fast = 1;

        while (fast < arr.length){
            if(arr[slow] == arr[fast]){
                fast ++;
            }else{
                slow++;
                arr[slow] = arr[fast];
                fast++;
            }
        }
        for (int i = 0; i < slow + 1; i++) {
            System.out.println(arr[i]);
        }
    }
}
