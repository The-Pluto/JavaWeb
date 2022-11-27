package src.house;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

@WebServlet("/add")
public class HouseAddServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String price = request.getParameter("price");
        String size = request.getParameter("size");
        String housesCount = request.getParameter("housesCount");
        String houseNumber = request.getParameter("houseNumber");

//        House house = new House();
//        house.setSize(Float.parseFloat(size));
//        house.setHouseNumber(houseNumber);
//        house.setHousesCount(Integer.parseInt(housesCount));
//        house.setPrice(Float.parseFloat(price));

        String message = "";
        try {
            HouseRepo.getInstance().HouseAdd(Float.parseFloat(price),Float.parseFloat(size),Integer.parseInt(housesCount),houseNumber);
            message = "�ύ��Ϣ����ɹ�";
        } catch (SQLException e) {
            e.printStackTrace();
            message = "�ύ��Ϣ����ʧ��";
        }
        response.setContentType("text/html;charset=UTF-8");
        try(Writer writer = response.getWriter()){
            String html = "<center style='margin-top:5em'><h1>%s</h1><br><br>" +
                    "<a href = './add.html' style=\"text-decoration:none;padding-right:5px\"> ¼���·��� </a>" +
                    "<a href = './listHouse' style=\"text-decoration:none;padding-right:5px\"> ��ʾ�б� </a>" +
                    "<a href = './index.html' style=\"text-decoration:none;padding-right:5px\"> ������ҳ </a>" +
                    "<a href = './admin.html' style=\"text-decoration:none;padding-right:5px\"> ���ع�������ҳ </a> </center>";
            writer.write(String.format(html,message));
        }

    }
}
