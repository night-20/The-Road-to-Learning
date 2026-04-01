package Method;

public class MethodDome3 {
    public static void main(String[] args) {

        double a = area(10.1,5.1);
        double b = area(20.6,7.2);

        if (a > b){
            System.out.println("第一个面积更大");
        }else if (a < b) {
            System.out.println("第二个面积更大");
        }else {
            System.out.println("两个面积一样大");
        }
    }

    public static double area (double len, double width){
        double area = len * width;
        return area;
    }
}
