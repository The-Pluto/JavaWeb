package swu.edu.cn.db.user;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import swu.edu.cn.db.db.DBEngine;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("user");
        String password = request.getParameter("password");

        try {
            User user = UserRepo.getinstance().auth(username,password);
            if(user != null) {
                response.sendRedirect("./admin.html");
            }
            else{
                response.sendRedirect("./index.html");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        for(User usr:users){
//
//        }

    }
}
