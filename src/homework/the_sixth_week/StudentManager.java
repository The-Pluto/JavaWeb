package homework.the_sixth_week;

import java.io.*;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.*;

public class StudentManager {
    Student student;
    public static ArrayList<Student> students = new ArrayList<>();

    public void AddStudent(Student student){
        students.add(student);
    }

    public StudentManager(){

    }

    public StudentManager(Student student){
        this.student = student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setStudents(ArrayList<Student> students){
        this.students = students;
    }

    public Student getStudent(){
        return this.student;
    }

    public ArrayList<Student> ScoreAnalysis(){
        for(Student stu:students){
            int total = stu.MathScore +
                    stu.ChineseScore + stu.EnglishScore;
            double average = total / 3.0;
            stu.totalScore = total;
            stu.averageScore = average;
        }
        return students;
    }
    public void PrintAll(){
        students = ScoreAnalysis();
        for(Student stu:students){
            System.out.format("%s, %s, %d, %d, %d, %d, %.2f\n",stu.id , stu.name ,
                    stu.MathScore , stu.getChineseScore() , stu.EnglishScore , stu.totalScore , stu.averageScore);
        }
        return;
    }

    public Student getStudentById(String id){
        for(Student stu:students){
            if(stu.id.equals(id)) {
                System.out.format("%s,%s,%d,%d,%d", stu.id + stu.name +
                        stu.ChineseScore + stu.MathScore + stu.EnglishScore);
                return stu;
            }
        }
        System.out.println("找不到该学生");
        return null;
    }

    public ArrayList<Student> findByScore(String classType, int min, int max){
        ArrayList<Student> stus = new ArrayList<>();
        String object = classType.toLowerCase();
        for(Student stu:students){
            if(object.equals("math")){
                if(stu.MathScore >= min && stu.MathScore <= max) stus.add(stu);
            }
            else if(object.equals("chinese")){
                if(stu.ChineseScore >= min && stu.ChineseScore <= max) stus.add(stu);
            }
            else if(object.equals("english")){
                if(stu.EnglishScore >= min && stu.EnglishScore <= max) stus.add(stu);
            }
            else System.out.println("输入课程类别无效");
        }
        return stus;
    }

    public ArrayList<Student> findByScore(int min, int max){
        ArrayList<Student> stus = new ArrayList<>();
        for(Student stu:students){
            double average = (stu.MathScore + stu.EnglishScore + stu.ChineseScore) / 3.0;
            if(average >= min && average <= max) stus.add(stu);
        }
        return stus;
    }

    public void sortByTotal(){
        Collections.sort(students);
    }

    public void saveAs(String filePath) throws IOException {
        File file = new File(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        for(Student stu:students){
            DecimalFormat df = new DecimalFormat("#.00");
            String str = stu.id + ", " + stu.name + ", " + stu.MathScore + ", " + stu.ChineseScore + ", " +
                    stu.EnglishScore + ", " + stu.totalScore + ", " + df.format(stu.averageScore) + '\n';
            bufferedWriter.write(str);
        }
        bufferedWriter.close();
    }

    public Student CreateStudent(){
        Student student = new Student();
        return student;
    }

    public static Student FindById(String id){
        for(Student stu:students){
            if(stu.id.equals(id)) return stu;
        }
        System.out.println("找不到该学生");
        return null;
    }
}
