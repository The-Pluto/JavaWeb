package swu.edu.cn.common;

import java.io.File;

public class SystemTest {

    public static void main(String[] args) {
        File temdir = new File(System.getProperty("java.io.tmpdir"));
        System.out.println(temdir.getAbsoluteFile());
    }
}
