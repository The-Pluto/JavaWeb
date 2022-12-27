package user;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@WebServlet("/getback")
public class GetBackServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String UUID = request.getParameter("UUID");
        User user = null;
        try {
            user = UserRepo.getinstance().findByUUID(UUID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(user != null){
            String username = user.getUsername();
            String password = user.getPassword();
        }

    }
}
