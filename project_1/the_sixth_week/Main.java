package project_1.the_sixth_week;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {


    private StudentManager studentManager;
    public static void main(String[] args) throws IOException, URISyntaxException {
        Main main = new Main();
        main.Init();

    }

    private void Start(){
        System.out.println("This is a Menu");
        System.out.println("1.Print All Students' Information");
        System.out.println("2.Find By Id:");
        System.out.println("3.Find By Average Score");
        System.out.println("4.Find By a Certain Class Score");
        System.out.println("5.Sort the Students By Total Score");
        System.out.println("6.Save the Students information in File");
        System.out.println("-1.exit the program");


//        System.out.println(StudentManager.students.size());
//        studentManager.sortByTotal();
//        studentManager.PrintAll();
//        studentManager.saveAs("D:\\Desktop\\copy.txt");
    }

    private void Init() throws IOException, URISyntaxException {
        String urifile = "D:\\Desktop\\student.txt";
        String uriMath = "http://139.186.26.196/javaweb/data/math.txt";
        String uriChinese = "http://139.186.26.196/javaweb/data/chinese.txt";
        String uriEnglish = "http://139.186.26.196/javaweb/data/english.txt";
        StudentReaderFactory studentReaderFactory = new StudentReaderFactory();
        StudentAbstractReader studentAbstractfileReader = studentReaderFactory.create(urifile);
        StudentAbstractReader studentAbstractMathReader = studentReaderFactory.create(uriMath);
        StudentAbstractReader studentAbstractChineseReader = studentReaderFactory.create(uriChinese);
        StudentAbstractReader studentAbstractEnglishReader = studentReaderFactory.create(uriEnglish);

        studentAbstractfileReader.read();
        studentAbstractMathReader.read();
        studentAbstractChineseReader.read();
        studentAbstractEnglishReader.read();
        this.studentManager = new StudentManager();

//        StudentFileReader studentFIleReader = new StudentFileReader(file);
//        StudentManager studentManager = new StudentManager();
//        studentManager.setStudents(studentFIleReader.LoadStudent());
//
//        StudentHttpReader studentHttpMathReader = new StudentHttpReader(Mathurl);
//        studentHttpMathReader.LoadStudentScore();
//        StudentHttpReader studentHttpChineseReader = new StudentHttpReader(Chineseurl);
//        studentHttpChineseReader.LoadStudentScore();
//        StudentHttpReader studentHttpEnglishReader = new StudentHttpReader(Englishurl);
//        studentHttpEnglishReader.LoadStudentScore();
//        StudentManager.ScoreAnalysis();

        Start();
        Scanner in = new Scanner(System.in);
        int type = in.nextInt();
        while(type != -1){
            if(type == 1) studentManager.PrintAll();
            else if(type == 2) {
                System.out.println("Input the id:");
                in.nextLine();
                String id = in.nextLine();
                studentManager.FindbyId(id);
            }
            else if(type == 3){
                System.out.println("Input the min AverageScore:");
                int min = in.nextInt();
                System.out.println("Input the max AverageScore:");
                int max = in.nextInt();
                studentManager.findByScore(min,max);
            }
            else if(type == 4){
                System.out.println("Input the class type:");
                in.nextLine();
                String classtype = in.nextLine();
                System.out.println("Input the min TotalScore:");
                int min = in.nextInt();
                System.out.println("Input the max TotalScore:");
                int max = in.nextInt();
                studentManager.findByScore(classtype,min,max);
            }
            else if(type == 5){
                studentManager.sortByTotal();
            }
            else if(type == 6){
                System.out.println("input the FilePath:");
                in.nextLine();
                String filepath = in.nextLine();
                studentManager.saveAs(filepath);
            }
            else{
                System.out.println("please input again");
            }
            Start();
            type = in.nextInt();
        }
        in.close();
    }
}
