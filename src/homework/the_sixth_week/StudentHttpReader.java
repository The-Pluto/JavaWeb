package homework.the_sixth_week;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class StudentHttpReader extends StudentAbstractReader{
    private URL url;
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

    public ArrayList<Student> LoadStudentScore() throws IOException{
        ArrayList<Student> students = new ArrayList<>();
        URLConnection urlConnection = this.getUrl().openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String string;
        while((string = bufferedReader.readLine()) != null){
            if(string.trim().isEmpty()) continue;

        }
        return null;
    }

    public Student CreateStudent(String stu){
        String[] str;
        str = stu.split("\t");
        Student student = new Student();
        student.id = str[0];
        student.name = str[1];
        return student;
    }

    @Override
    public FileInputStream GetInputStreamReader() throws FileNotFoundException {
        return null;
    }
}
