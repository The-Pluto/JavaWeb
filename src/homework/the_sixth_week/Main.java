package homework.the_sixth_week;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        File file = new File("D:\\Desktop\\student.txt");
        URL Mathurl = new URL("http://139.186.26.196/javaweb/data/math.txt");
        URL Chineseurl = new URL("http://139.186.26.196/javaweb/data/chinese.txt");
        URL Englishurl = new URL("http://139.186.26.196/javaweb/data/english.txt");

        StudentFIleReader studentFIleReader = new StudentFIleReader(file);
        StudentManager studentManager = new StudentManager();
        studentManager.setStudents(studentFIleReader.LoadStudent());

        StudentHttpReader studentHttpMathReader = new StudentHttpReader(Mathurl);
        studentHttpMathReader.LoadStudentScore();
        StudentHttpReader studentHttpChineseReader = new StudentHttpReader(Chineseurl);
        studentHttpChineseReader.LoadStudentScore();
        StudentHttpReader studentHttpEnglishReader = new StudentHttpReader(Englishurl);
        studentHttpEnglishReader.LoadStudentScore();
        studentManager.PrintAll();


    }
}
