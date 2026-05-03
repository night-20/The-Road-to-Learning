import java.util.ArrayList;

public class School {
    private ArrayList<String> students = new ArrayList<>();

    public ArrayList<String> getStudent() {
        return students;
    }

    public void addStudent(String name) {
        students.add(name);
    }

    public void remove(int number) {
        students.remove(number);
    }

    public void updateStudent(int number, String newName) {
        students.set(number,newName);
    }
}