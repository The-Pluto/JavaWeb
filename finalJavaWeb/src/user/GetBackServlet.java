package user;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

@WebServlet("/getback")
public class GetBackServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uuid = request.getParameter("UUID");
        User user = null;
        try {
            user = UserRepo.getinstance().findByUUID(uuid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.setContentType("text/html;charset=UTF-8");
        String message = "";
        if(user == null){
            message = "未查询到该账号";
            try(Writer writer = response.getWriter()){
                String html = "<center style='margin-top:5em'><h1>%s</h1><br><br>" +
                        "<a href = './forgetpassword.html'> 重新找回 </a>" +
                        "<a href = './index.html'> 返回主页 </a> </center>";
                writer.write(String.format(html,message));
            }
        }
        else{
            message = "找到账号,已将密码重置为123456";
            try(Writer writer = response.getWriter()){
                String html = "<center style='margin-top:5em'><h1>%s</h1><br><br>" +
                        "<a href = './login.html'> 登录 </a>" +
                        "<a href = './index.html'> 返回主页 </a> </center>";
                writer.write(String.format(html,message));
            }
            try {
                UserRepo.getinstance().changePassword(user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
