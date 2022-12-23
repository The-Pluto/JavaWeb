package swu.edu.cn.db;


import swu.edu.cn.db.book.Book;
import swu.edu.cn.db.book.BookRepo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/index-old.html")
public class Index extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
            sb.append("<div class='book-pic'><img src=\"./upload/" + book.getPicture() + "\" style='width:150px'/></div>");
            sb.append("<div class='book-name'>" + book.getName() + "</div>");
            sb.append("<div class='book-author'>����: " + book.getAuthor() + "</div>");
            sb.append("<div class='book-price'>�۸�" + book.getPrice()+ "</div>");
            sb.append("</div>\n");
        }
        sb.append("</div><br>\n");

        String page = "<html>\n" +
                "    <head>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <title>My Java Web APP</title>\n" +
                "        <link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\">" +
                "    </head>\n" +
                "    <body>\n" +
                "        <center style=\"margin-top:1em\">\n" +
                "            <h1>��ӭ�����ҵ��������</h1>\n" +
                "            <div style=\"margin-top:2em; width: 50%\">\n" +
//                "                <div>\n" +
//                "                    <div style=\"float:left;padding-right:3em\"><a href=\"./listBook\">�鿴ͼ��</a></div>\n" +
//                "                </div>\n" +
                "                <div>\n" +
                "                    <div style=\"float:left;padding-right:3em\"><a href=\"./login\">��¼��̨</a></div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <br><hr width='80%'>\n" +
                "          "  + sb.toString() +
                "        </center>\n" +
                "    </body>\n" +
                "</html>";

        response.setContentType("text/html; Charset=utf8");
        try(Writer writer = response.getWriter()) {
            writer.write(page);
        }
    }

}