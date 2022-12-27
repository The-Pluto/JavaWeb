package user;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@WebServlet("/SignUp")
public class SignUpUserServlet extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "upload";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String nickname = request.getParameter("user");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UUID UUID = java.util.UUID.randomUUID();

        User user = new User(username,password,String.valueOf(UUID),nickname);
        String message = null;
        try {
            UserRepo.getinstance().save(user);
            message = "注册成功";
        }catch (SQLException e) {
            e.printStackTrace();
            message = "注册失败";
        }
        System.out.println(message);

        response.setContentType("application/json;charset=UTF-8");
        try(Writer writer = response.getWriter()){
            this.writeJsonByJackson(writer,message);
        }
        response.sendRedirect("./feedback.html");
    }

    private void writeJsonByJackson(Writer writer, String message) throws IOException {
        String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(message);
        writer.write(json);
        System.out.println("json:" + json);
    }

}

