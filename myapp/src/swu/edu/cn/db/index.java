package swu.edu.cn.db;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import swu.edu.cn.db.book.Book;
import swu.edu.cn.db.book.BookRepo;
import swu.edu.cn.db.db.DBEngine;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/index.html")
public class index extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        List<Book> books = null;
        try {
            books = BookRepo.getBookRepo().getAllBook();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<br><div class='book-group'>\n");
        for (Book book : books) {
            sb.append("<div class='book-div'>");
            sb.append("<div class='book-pic'><img src=\"./images/logo.jpg\"/></div>");
            sb.append("<div class='book-name'>" + book.getName() + "</div>");
            sb.append("<div class='book-author'>作者: " + book.getAuthor() + "</div>");
            sb.append("<div class='book-price'>价格：" + book.getPrice()+ "</div>");
            sb.append("</div>\n");
        }
        sb.append("</div><br>\n");

        String page = "<html>\n" +
                "<head>\n" +
                "   <meta charset=\"UTF-8\">\n" +
                "   <title>My Java Web APP</title>\n" +
                "   <link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\">" +
                "</head>\n" +
                "<body>\n" +
                "<center style=\"margin-top:5em\">\n" +
                "  <h1>欢迎访问我的网上书店</h1>\n" +
                "  <div style=\"margin-top:2em; width: 50%\">\n" +
                "    <div>\n" +
                "      <div style=\"float:left;padding-right:3em\"><a href=\"./listBook\" style=\"text-decoration:none\">查 看 数 目</a></div>\n" +
                "      <div style=\"float:left;padding-right:3em\"><a href=\"./login.html\" style=\"text-decoration:none\">管 理 员 登 录</a></div>\n" +
                "    </div>\n" +
                "  </div>\n" +

                "  <br><hr width='80%'>\n" +
                "  <br><br>\n" +
                "\n" +
                sb.toString() +
                "</center>\n" +
                "</body>\n" +
                "</html>";

        response.setContentType("text/html; Charset=utf8");
        try(Writer writer = response.getWriter()){
            writer.write(page);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
