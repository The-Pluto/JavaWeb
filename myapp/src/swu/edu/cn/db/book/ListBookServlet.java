package swu.edu.cn.db.book;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import swu.edu.cn.db.db.DBEngine;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listBook")
public class ListBookServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            List<Book> books = BookRepo.getBookRepo().getAllBook();
            response.setContentType("text/html");
            try(Writer writer = response.getWriter()) {

                writer.write("<h1 style=\"text-align:center;margin:50px auto\">欢迎访问线上书店</h1>");

                writer.write("<table style = \"text-align:center;margin:0 auto\">");
                for(int i=0 ; i<books.size(); ++i){
                    Book book = books.get(i);
                    if(i%2 == 0){
                        writer.write("<tr style='background-color:#F5F5F5;height:2em'>");
                    }
                    else{
                        writer.write("<tr style='background-color:#D6E6F2;height:2em'>");
                    }
                    writer.write(String.format("<td width='30px'>%s</td>",book.getId()));
                    writer.write(String.format("<td width='150px'>%s</td>",book.getName()));
                    writer.write(String.format("<td width='100px'>%s</td>",book.getAuthor()));
                    writer.write(String.format("<td width='60px'>%s</td>",book.getPrice()));
                    writer.write(String.format("<td width='200px'>%s</td>",book.getDescribe()));
                    writer.write(String.format("<td><a href='./deleteBook?id=%s'>" +
                            "<img src='./images/delete.jpg' width = '30px'></a></td>",book.getId()));
                    writer.write(String.format("<td><a href='./editBook?id=%s'>" +
                            "<img src='./images/edit.jpg' width = '30px'></a></td>",book.getId()));
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
