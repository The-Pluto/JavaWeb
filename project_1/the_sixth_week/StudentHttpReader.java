package project_1.the_sixth_week;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class StudentHttpReader extends StudentAbstractReader{
    protected URL url;

    public StudentHttpReader(URL url){
        this.url = url;
    }

    public StudentHttpReader(){

    }

    public void setUrl(URL url){
        this.url = url;
    }

    public URL getUrl(){
        return this.url;
    }

//    public ArrayList<Student> LoadStudentScore() throws IOException{
//        ArrayList<Student> students = StudentManager.students;
//        URLConnection urlConnection = this.getUrl().openConnection();
//        InputStream inputStream = urlConnection.getInputStream();
//        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//        String string;
//        while((string = bufferedReader.readLine()) != null){
//            if(string.trim().isEmpty()) continue;
//            Student student = AddStudentInformation(string);
//        }
//        bufferedReader.close();
//        return students;
//    }

//    public Student AddStudentInformation(String stu){
//        String[] str;
//        str = stu.split("\t");
//        StudentManager studentManager = new StudentManager();
//        Student student = studentManager.FindById(str[0]);
//        if(student == null) return null;
//        String object = StudentSubjectReader.getSubject(this.url);
//        int score = Integer.parseInt(str[1]);
//        if(object.equals("math.txt")) student.MathScore = score;
//        else if(object.equals("english.txt")) student.EnglishScore = score;
//        else if(object.equals("chinese.txt")) student.ChineseScore = score;
//        return student;
//    }

    @Override
    public InputStreamReader GetInputStreamReader() throws IOException {
        ArrayList<Student> students = StudentManager.students;
        URLConnection urlConnection = this.getUrl().openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        return inputStreamReader;
    }
    @Override
    public String GetURI(){
        return this.url.getPath();
    }
}
