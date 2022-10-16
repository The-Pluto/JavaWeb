package homework.the_sixth_week;

import java.net.URL;

public abstract class StudentSubjectReader {
    private URL url;

    public void setUrl(URL url){
        this.url = url;
    }

    public URL getUrl(){
        return this.url;
    }

    public static String getSubject(URL url){
        String str = url.getFile();
        String[] strs = str.split("/");
        String object = strs[strs.length - 1];
        return object;
    }
}
