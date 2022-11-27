package src.user;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import src.house.HouseRepo;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteUser")
public class UserDeleteServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        try {
            UserRepo.getInstance().DeleteByUserName(username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("./listUser");
    }
}
