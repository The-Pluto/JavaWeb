package homework.the_sixth_week;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentFIleReader extends StudentAbstractReader{
    private File file;


    public StudentFIleReader(File file){
        this.file = file;
    }

    public void setFile(File file){
        this.file = file;
    }

    public File getFile(){
        return this.file;
    }

    @Override
    public FileInputStream GetInputStreamReader() throws FileNotFoundException {
        return null;
//        return new FileInputStream(this.getFile());
    }

    private StudentFIleReader(){

    }

    public ArrayList<Student> LoadStudent() throws IOException {
        ArrayList<Student> students = new ArrayList<>();
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

    public Student CreateStudent(String stu){
        String[] str;
        str = stu.split("\t");
        Student student = new Student();
        student.id = str[0];
        student.name = str[1];
        return student;
    }

}
