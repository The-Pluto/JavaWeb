package project_1.the_sixth_week;

import java.io.*;
import java.util.ArrayList;

public class StudentFileReader extends StudentAbstractReader{
    private File file;


    public StudentFileReader(File file){
        this.file = file;
    }

    public void setFile(File file){
        this.file = file;
    }

    public File getFile(){
        return this.file;
    }

    @Override
    public InputStreamReader GetInputStreamReader() throws FileNotFoundException {
        ArrayList<Student> students = StudentManager.students;
        File file = this.file;
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        return inputStreamReader;
    }

    @Override
    public String GetURI() {
        return this.file.getPath();
    }


    private InputStreamReader StudentFIleReader() throws FileNotFoundException {
        return null;
    }

    public ArrayList<Student> LoadStudent() throws IOException {
        ArrayList<Student> students = StudentManager.students;
        File file = this.file;
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String string;
        while((string = bufferedReader.readLine()) != null){
            if(string.trim().isEmpty()) continue;
            Student student = this.CreateStudent(string);
            students.add(student);
        }
        bufferedReader.close();
        return students;
    }

//    public Student CreateStudent(String stu){
//        String[] str;
//        str = stu.split("\t");
//        Student student = new Student();
//        student.id = str[0];
//        student.name = str[1];
//        return student;
//    }

}
