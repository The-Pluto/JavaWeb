package swu.edu.cn.db.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import java.io.IOException;
import java.util.SortedMap;

@WebServlet("/test")
public class ServletTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getServletContext().getRealPath("./"));
        System.out.println(request.getRequestURI());
        System.out.println(request.getContextPath());
        System.out.println(request.getRequestedSessionId());

    }
}
