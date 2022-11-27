package src.user;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

@WebServlet("/register")
public class UserRegisterServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("text/html;charset=UTF-8");
        String message = null;
        try {
            User user = UserRepo.getInstance().userAuth(username);
            if(user != null){
                message ="���û��ѱ�ע��";
                try(Writer writer = response.getWriter()){
                    String html = "<center style='margin-top:5em'><h1>%s</h1><br><br>" +
                            "<a href = './register.html' style=\"text-decoration:none;padding-right:5px\"> ����ע�� </a>" +
                            "<a href = './index.html' style=\"text-decoration:none;padding-right:5px\"> ������ҳ </a> </center>";
                    writer.write(String.format(html,message));
                }
                return;
            }
            else{
                UserRepo.getInstance().save(username,password);
                message = "�û�ע��ɹ�";
                try(Writer writer = response.getWriter()){
                    String html = "<center style='margin-top:5em'><h1>%s</h1><br><br>" +
                            "<a href = './user.html' style=\"text-decoration:none;padding-right:5px\"> �����û�ҳ�� </a>" +
                            "<a href = './index.html' style=\"text-decoration:none;padding-right:5px\"> ������ҳ </a> </center>";
                    writer.write(String.format(html,message));
                }
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            message = "ע��ʧ��";
        }


        try(Writer writer = response.getWriter()){
            String html = "<center style='margin-top:5em'><h1>%s</h1><br><br>" +
                    "<a href = './index.html' style=\"text-decoration:none;padding-right:5px\"> ������ҳ </a>" +
                    "<a href = './register.html' style=\"text-decoration:none;padding-right:5px\"> ����ע�� </a>";
            writer.write(String.format(html,message));
        }

    }
}
