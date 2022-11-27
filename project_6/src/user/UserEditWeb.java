package src.user;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import src.house.House;
import src.house.HouseRepo;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

@WebServlet("/editUser")
public class UserEditWeb extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        this.doPost(request,response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String username = String.valueOf(session.getAttribute(LoginServlet.LOGIN_USER));

        User user = null;
        try {
            user = UserRepo.getInstance().getUserByUsername(username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Add new user</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "      <h1 style=\"padding-top:1em;text-align:center\">个人信息修改</h1><br><br>\n" +
                "\n" +
                "          <div style=\"width:45%\">\n" +
                "              <div style=\"float:right\">姓名</div><br><br>\n" +
                "              <div style=\"float:right\">年龄</div><br><br>\n" +
                "              <div style=\"float:right\">性别</div><br><br>\n" +
                "              <div style=\"float:right\">身份证号</div><br><br>\n" +
                "              <div style=\"float:right\">房间号</div><br><br>\n" +
                "          </div>\n" +
                "      <form action=\"./editInfo\" style=\"width:53%;float:right;margin-top:-212px\" method=\"post\">\n" +
                "        <input type=\"text\" name=\"name\" value=\""+ user.getName() + "\"><br><br>\n" +
                "        <input type=\"text\" name=\"age\" value=\""+ user.getAge() + "\"><br><br>\n" +
                "        <input type=\"text\" name=\"sex\" value=\""+ user.getSex() + "\"><br><br>\n" +
                "        <input type=\"text\" name=\"id\" value=\""+ user.getId() + "\"><br><br>\n" +
                "        <input type=\"text\" name=\"houseNumber\" value=\""+ user.getHouseNumber() + "\"><br><br>\n" +
                "        <input type=\"submit\"><br><br>\n" +
                "      </form>\n" +
                "        <div style=\"text-align:center;margin-top:100px\">\n" +
                "            <a href=\"./admin.html\" style=\"text-decoration:none\">返 回 管 理 者 菜 单</a>\n" +
                "        </div>\n" +
                "</body>\n" +
                "</html>";

        response.setContentType("text/html; Charset=utf8");
        try(Writer writer = response.getWriter()){
            writer.write(html);
        }
    }

}
