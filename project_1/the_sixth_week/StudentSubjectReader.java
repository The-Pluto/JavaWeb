package project_1.the_sixth_week;

import java.net.URL;

public class StudentSubjectReader extends StudentHttpReader{

    public void setUrl(URL url){
        this.url = url;
    }

    public URL getUrl(){
        return this.url;
    }

    public static String getSubject(String url){
        String[] strs = url.split("/");
        String object = strs[strs.length - 1];
        return object;
    }
}
