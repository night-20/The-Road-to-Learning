//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class dome01 {
//    public static void main(String[] args) {
//        ArrayList<String> students = new ArrayList<>();
//        students.add("小明");
//        students.add("小红");
//
//        HashMap<String,String> cityMap = new HashMap<>();
//        cityMap.put(students.get(0),"广州");
//        cityMap.put(students.get(1),"深圳");
//
//        //修改
//        students.set(1,"红凤凰");
//        cityMap.put(students.get(1),"北京");
//
//        //删除
//        students.remove(0);
//
//        for (int i = 0; i < students.size(); i++) {
//            String name = students.get(i);
//            if (cityMap.get(name) != null)
//            System.out.println(cityMap.get(name));
//        }
//
//        System.out.println("列表人数：" + students.size());
//        System.out.println("小明还在地图里吗：" + cityMap.get("小明"));
//    }
//}