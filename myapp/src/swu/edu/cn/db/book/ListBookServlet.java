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
                for (Book book : books) {
                    writer.write("<tr>");
                    String str = " <td>%s</td><td>%s</td><td>%s</td><td>%s</td>";
                    writer.write(String.format(str, book.getId(), book.getName(), book.getAuthor(), book.getPrice()));
                    writer.write("</tr>");
                }
                writer.write("</table>");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
