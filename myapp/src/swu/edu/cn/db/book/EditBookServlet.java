package swu.edu.cn.db.book;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

@WebServlet("/editBook")
public class EditBookServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        Book book = null;
        try{
            book = BookRepo.getBookRepo().getById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>My Book Store</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<center>\n" +
                "  <div style=\"margin-top:5em; padding:2em; text-align:center;width:60%;background-color:#EEEEE\">\n" +
                "    <h2>修改图书信息</h2>\n" +
                "    <form action=\"./saveBook\" method=\"post\">\n" +
                "      编 号: <input type=\"hidden\" name=\"id\" value = \""+ book.getId() + "\"></label><br><br>" +
                "      书 名: <input type=\"text\" name=\"name\" value = \"" + book.getName() +"\"><br><br>\n" +
                "      作 者: <input type=\"text\" name=\"author\" value = \"" + book.getAuthor() + "\"><br><br>\n" +
                "      价 格: <input type=\"text\" name=\"price\" value = \"" + book.getPrice() + "\"><br><br>\n" +
                "      简 介: <textarea name=\"describe\" rows=\"4\" cols=\"22\"> " + book.getDescribe() + "</textarea><br><br>\n" +
                "      <input type=\"submit\" value=\"提交信息\">\n" +
                "    </form>\n" +
                "  </div>\n" +
                "</center>\n" +
                "</body>\n" +
                "</html>";

        response.setContentType("text/html; Charset=utf8");
        try(Writer writer = response.getWriter()){
            writer.write(html);
        }


    }
}
