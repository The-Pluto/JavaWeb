package src.user;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import src.house.HouseRepo;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

@WebServlet("/editInfo")
public class UserEditServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String username = String.valueOf(session.getAttribute(LoginServlet.LOGIN_USER));
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String sex = request.getParameter("sex");
        String id = request.getParameter("id");
        String houseNumber = request.getParameter("houseNumber");


        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setAge(Integer.parseInt(age));
        user.setSex(sex);
        user.setId(id);
        user.setHouseNumber(houseNumber);

        String message = null;
        try {
            UserRepo.getInstance().editUserByUsername(user);
            message = "信息修改成功";
        } catch (SQLException e) {
            e.printStackTrace();
            message = "信息修改失败!";
        }

        response.setContentType("text/html;charset=UTF-8");
        try(Writer writer = response.getWriter()){
            String html = "<center style='margin-top:5em'><h1>%s</h1><br><br>" +
                    "<a href = './index.html' style=\"text-decoration:none;padding-right:5px\"> 返回主页 </a>" +
                    "<a href = './user.html' style=\"text-decoration:none;padding-right:5px\"> 返回用户界面 </a> </center>";
            writer.write(String.format(html,message));
        }
    }
}
