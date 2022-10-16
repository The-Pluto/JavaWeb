package project_1.the_sixth_week;

import java.io.*;
import java.util.ArrayList;

abstract class StudentAbstractReader implements IsStudentReader{

    public abstract InputStreamReader GetInputStreamReader() throws IOException;

    public abstract String GetURI();
    public ArrayList<Student> read() throws IOException {
        ArrayList<Student> students = StudentManager.students;
        InputStreamReader inputStreamReader = this.GetInputStreamReader();
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String string;
        while((string = bufferedReader.readLine()) != null){
            if(string.trim().isEmpty()) continue;
            Student student = this.CreateStudent(string);
        }
        bufferedReader.close();
        return students;
    }

    public Student CreateStudent(String stu){
        String[] str;
        str = stu.split("\t");
        StudentManager studentManager = new StudentManager();
        Student hasstudent = studentManager.FindById(str[0]);
        if(hasstudent == null){
            Student student = new Student();
            student.id = str[0];
            student.name = str[1];
            StudentManager.students.add(student);
            return student;
        }
        else{
            hasstudent = AddStudentInformation(str,hasstudent);
            return hasstudent;
        }
    }

    public Student AddStudentInformation(String[] str,Student student){
        String object = this.GetURI();
        String[] strs = object.split("/");
        int score = Integer.parseInt(str[1]);
        if(strs[strs.length-1].equals("math.txt")) student.MathScore = score;
        else if(strs[strs.length-1].equals("english.txt")) student.EnglishScore = score;
        else if(strs[strs.length-1].equals("chinese.txt")) student.ChineseScore = score;
        return student;
    }

}
