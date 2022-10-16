package homework.the_sixth_week;

import java.io.*;
import java.util.ArrayList;

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

    public void PrintAll(){
        for(Student stu:students){
            System.out.format("%s, %s, %s, %s, %s\n",stu.id , stu.name , stu.MathScore , stu.getChineseScore() , stu.EnglishScore);
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
        for(Student stu:students){

        }
        return stus;
    }

    public Student findByScore(int min, int max){
        Student student = new Student();
        return student;
    }

    public void sortByTotal(){
        return;
    }

    public void saveAs(String filePath){
        return;
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
