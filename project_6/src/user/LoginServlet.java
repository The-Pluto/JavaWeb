package src.user;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public final static String LOGIN_TOKEN = "USER_LOGIN_TOKEN";
    public final static String LOGIN_USER = "RECORD_USERNAME";

    public final static String LOGIN_CODE = "LOGIN_CODE";
    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        this.doPost(request,response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String verificationCode = request.getParameter("code");


        if(username != null && password != null){
            try {
                this.doLogin(request,response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            HttpSession session = request.getSession(Boolean.FALSE);
            if(session == null || session.getAttribute(LoginServlet.LOGIN_TOKEN) == null){
                response.sendRedirect("./login.html");
            }else{
                if(session.getAttribute(LoginServlet.LOGIN_TOKEN).equals("admin")){
                    response.sendRedirect("./admin.html");
                } else{
                    response.sendRedirect("./user.html");
                }
            }
        }
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String verificationCode = request.getParameter("code");
        String codeInSession = (String)request.getSession(true).getAttribute(ValidateCodeServlet.LOGIN_CODE);
        if(!verificationCode.equalsIgnoreCase(codeInSession)){
            System.out.println("��֤�����");
            response.sendRedirect("./login");
            return;
        }
        User user = UserRepo.getInstance().userAuth(username,password);
        if(user != null){
            HttpSession session = request.getSession();
            if(user.getStatus().equals("����Ա")){
                session.setAttribute(LoginServlet.LOGIN_TOKEN,"admin");
                response.sendRedirect("./admin.html");
            }
            else{
                session.setAttribute(LoginServlet.LOGIN_TOKEN,"guest");
                session.setAttribute(LoginServlet.LOGIN_USER,String.format(username));
                response.sendRedirect("./user.html");
            }
            System.out.println("��¼�ɹ�");
        }
        else{
            System.out.println("�û����������������");
            response.sendRedirect("./index.html");
        }

    }
}
