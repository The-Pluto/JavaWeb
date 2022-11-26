package src.user;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import src.db.DBEngine;
import src.db.RecordVisitor;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        this.doPost(request,response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String verificationCode = request.getParameter("code");

        if(username == null && password == null){
            System.out.println("session不存在，需要登陆");
            response.sendRedirect("./login.html");
            return;
        }

        try {
            this.getUserFromDB(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private void getUserFromDB(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String verificationCode = request.getParameter("code");

        String template = "SELECT * FROM user WHERE `username`='%s' && `password`='%s'";
        String sql = String.format(template,username,password);
//        System.out.println(sql);
        List<User> users = DBEngine.getInstance().query(sql, new RecordVisitor<User>() {
            @Override
            public User visit(ResultSet rs) throws SQLException {
                return UserRepo.getInstance().getUserFromResultSet(rs);
            }
        });

        User user = users.size() == 0 ? null : users.get(0);
        if(user != null){
            System.out.println("登录成功");
            response.sendRedirect("./admin.html");
        }
        else{
            System.out.println("用户名或密码输入错误");
            response.sendRedirect("./index.html");
        }

    }
}
