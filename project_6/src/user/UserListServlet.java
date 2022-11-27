package src.user;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import src.house.House;
import src.house.HouseRepo;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listUser")
public class UserListServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        this.doPost(request,response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            List<User> users = UserRepo.getInstance().getAllUsers();
            response.setContentType("text/html");
            try(Writer writer = response.getWriter()) {

                writer.write("<h1 style=\"text-align:center;margin:50px auto\">用 户 信 息</h1>");
                writer.write("<div style=\"text-align:center;height:22px\">");
                writer.write("<div style=\"text-align:center;float:left;padding-left:270px\">姓名</div>");
                writer.write("<div style=\"text-align:center;float:left;padding-left:20px\">年龄</div>");
                writer.write("<div style=\"text-align:center;float:left;padding-left:5px\">性别</div>");
                writer.write("<div style=\"text-align:center;float:left;padding-left:50px\">身份证</div>");
                writer.write("<div style=\"text-align:center;float:left;padding-left:70px\">房间号</div>");
                writer.write("<div style=\"text-align:center;float:left;padding-left:20px\">用户名</div>");
                writer.write("</div>");
                writer.write("<table style = \"text-align:center;margin:0 auto\">");
                for(int i=0 ; i<users.size(); ++i){
                    User user = users.get(i);
                    if(i%2 == 0){
                        writer.write("<tr style='background-color:#F5F5F5;height:2em'>");
                    }
                    else{
                        writer.write("<tr style='background-color:#D6E6F2;height:2em'>");
                    }
                    writer.write(String.format("<td width='50px'>%s</td>",user.getName()));
                    writer.write(String.format("<td width='30px'>%s</td>",user.getAge()));
                    writer.write(String.format("<td width='30px'>%s</td>",user.getSex()));
                    writer.write(String.format("<td width='50px'>%s</td>",user.getId()));
                    writer.write(String.format("<td width='30px'>%s</td>",user.getHouseNumber()));
                    writer.write(String.format("<td width='50px'>%s</td>",user.getUsername()));
                    writer.write(String.format("<td><a href='./deleteUser?username=%s'>" +
                            "<img src='./images/delete.jpg' width = '30px'></a></td>",user.getUsername()));
                    writer.write(String.format("<td><a href='./editUser?username=%s'>" +
                            "<img src='./images/edit.jpg' width = '30px'></a></td>",user.getUsername()));
                    writer.write("</tr>");
                }
                writer.write("</table><br><br>");

                writer.write("<center><a href = 'index.html'> 返回首页 </a></center>");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
