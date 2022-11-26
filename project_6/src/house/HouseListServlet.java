package src.house;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listHouse")
public class HouseListServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        this.doPost(request,response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            List<House> houses = HouseRepo.getInstance().GetAllHouse();
            response.setContentType("text/html");
            try(Writer writer = response.getWriter()) {

                writer.write("<h1 style=\"text-align:center;margin:50px auto\">房 屋 信 息</h1>");
                writer.write("<div style=\"text-align:center;height:22px\">");
                writer.write("<div style=\"text-align:center;float:left;padding-left:350px\">价 格</div>");
                writer.write("<div style=\"text-align:center;float:left;padding-left:20px\">面 积</div>");
                writer.write("<div style=\"text-align:center;float:left;padding-left:30px\">房间数</div>");
                writer.write("<div style=\"text-align:center;float:left;padding-left:20px\">房间号</div>");
                writer.write("</div>");
                writer.write("<table style = \"text-align:center;margin:0 auto\">");
                for(int i=0 ; i<houses.size(); ++i){
                    House house = houses.get(i);
                    if(i%2 == 0){
                        writer.write("<tr style='background-color:#F5F5F5;height:2em'>");
                    }
                    else{
                        writer.write("<tr style='background-color:#D6E6F2;height:2em'>");
                    }
                    writer.write(String.format("<td width='50px'>%.2f</td>",house.getPrice()));
                    writer.write(String.format("<td width='50px'>%.2f</td>",house.getSize()));
                    writer.write(String.format("<td width='50px'>%d</td>",house.getHousesCount()));
                    writer.write(String.format("<td width='100px'>%s</td>",house.getHouseNumber()));
                    writer.write(String.format("<td><a href='./delete?houseNumber=%s'>" +
                            "<img src='./images/delete.jpg' width = '30px'></a></td>",house.getHouseNumber()));
                    writer.write(String.format("<td><a href='./edit?houseNumber=%s'>" +
                            "<img src='./images/edit.jpg' width = '30px'></a></td>",house.getHouseNumber()));
                    writer.write("</tr>");
                }
                writer.write("</table><br><br>");

                writer.write("<center><a href = 'index.html'> 返回首页 </a></center>");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
