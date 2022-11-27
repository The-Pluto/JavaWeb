package src.user;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Writer;
import java.net.URI;

@WebServlet("/authLimit")
public class AuthLimit extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request,response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String uri = String.valueOf(session.getAttribute(AuthFilter.URI));
        System.out.println(uri);
        String message = "";
        response.setContentType("text/html;charset=UTF-8");
        try(Writer writer = response.getWriter()){
            String html = "<center style='margin-top:5em'><h1>你没有权限访问该网页</h1><br><br>" +
                    "<a href = 'http://localhost:8080" + uri + "' style=\"text-decoration:none;padding-right:5px\"> 返回 </a>";
            writer.write(html);
        }
    }
}