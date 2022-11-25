package swu.edu.cn.db.user;

import com.mysql.cj.Session;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import swu.edu.cn.db.db.DBEngine;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public final static String LOGIN_TOKEN = "USER_LOGIN_TOKEN";

    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        this.doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("user");
        String password = request.getParameter("password");
        if(username != null && password != null){
            this.doLogin(request,response);
        }else {
            HttpSession session = request.getSession();
            if(session == null || session.getAttribute(LoginServlet.LOGIN_TOKEN) != Boolean.TRUE){
                response.sendRedirect("./login.html");
            }else {
                response.sendRedirect("./admin.html");
            }
        }
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("user");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        String codeInSession = (String)request.getSession(true).getAttribute(ValidateCodeServlet.LOGIN_CODE);
        if(!code.equalsIgnoreCase(codeInSession)){
            System.out.println("验证码错误");
            response.sendRedirect("./login");
            return;
        }
        try {
            User user = UserRepo.getinstance().auth(username,password);
            if(user != null) {
                HttpSession session = request.getSession(); //建立session
                session.setAttribute(LOGIN_TOKEN, Boolean.TRUE);//设置属性
                response.sendRedirect("./admin.html");
            }
            else{
                response.sendRedirect("./index.html");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
