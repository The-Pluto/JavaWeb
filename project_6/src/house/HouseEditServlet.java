package src.house;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.util.Locale;

@WebServlet("/editHouse")
public class HouseEditServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String price = request.getParameter("price");
        String size = request.getParameter("size");
        String housesCount = request.getParameter("housesCount");
        String houseNumber = request.getParameter("houseNumber");
        House house = new House();
        house.setSize(Float.parseFloat(size));
        house.setHousesCount(Integer.parseInt(housesCount));
        house.setPrice(Float.parseFloat(price));
        house.setHouseNumber(houseNumber);
        try {
            HouseRepo.getInstance().EditByHouseNumber(house);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
