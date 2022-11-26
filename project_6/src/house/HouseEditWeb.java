package src.house;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

@WebServlet("/edit")
public class HouseEditWeb extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        this.doPost(request,response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String houseNumber = request.getParameter("houseNumber");
        House house = null;
        try {
            house = HouseRepo.getInstance().GetByHouseNumber(houseNumber);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Add new house</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "      <h1 style=\"padding-top:1em;text-align:center\">�� �� �� ��</h1><br><br>\n" +
                "\n" +
                "          <div style=\"width:45%\">\n" +
                "              <div style=\"float:right\">���ݼ۸�(��ÿƽ��)</div><br><br>\n" +
                "              <div style=\"float:right\">�������(ƽ����)</div><br><br>\n" +
                "              <div style=\"float:right\">��������</div><br><br>\n" +
                "              <div style=\"float:right\">�����</div><br><br>\n" +
                "          </div>\n" +
                "      <form action=\"./editHouse\" style=\"width:53%;float:right;margin-top:-170px\" method=\"post\">\n" +
                "        <input type=\"text\" name=\"price\" value=\""+ house.getPrice() + "\"><br><br>\n" +
                "        <input type=\"text\" name=\"size\" value=\""+ house.getSize() + "\"><br><br>\n" +
                "        <input type=\"text\" name=\"housesCount\" value=\""+ house.getHousesCount() + "\"><br><br>\n" +
                "        <input type=\"text\" name=\"houseNumber\" value=\""+ house.getHouseNumber() + "\"><br><br>\n" +
                "        <input type=\"submit\"><br><br>\n" +
                "      </form>\n" +
                "        <div style=\"text-align:center;margin-top:100px\">\n" +
                "            <a href=\"./admin.html\" style=\"text-decoration:none\">�� �� �� �� �� �� ��</a>\n" +
                "        </div>\n" +
                "</body>\n" +
                "</html>";

        response.setContentType("text/html; Charset=utf8");
        try(Writer writer = response.getWriter()){
            writer.write(html);
        }
    }

}
