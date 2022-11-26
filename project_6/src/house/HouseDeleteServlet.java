package src.house;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete")
public class HouseDeleteServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String houseNumber = request.getParameter("houseNumber");
        try {
            HouseRepo.getInstance().DeleteByHouseNumber(houseNumber);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("./listHouse");
    }

}
