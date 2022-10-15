package homework.the_sixth_week;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        File file = new File("D:\\Desktop\\student.txt");
        StudentFIleReader studentFIleReader = new StudentFIleReader(file);
        StudentManager studentManager = new StudentManager();
        studentManager.setStudents(studentFIleReader.LoadStudent());
        studentManager.PrintAll();
        URL url = new URL("http://139.186.26.196/javaweb/data/math.txt");
        System.out.println(url.toString());
    }
}
