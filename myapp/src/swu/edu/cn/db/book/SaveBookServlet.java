package swu.edu.cn.db.book;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

@WebServlet("/saveBook")
public class SaveBookServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String describe = request.getParameter("describe");
        String price = request.getParameter("price");


        Book book = new Book();
        book.setAuthor(author);
        book.setDescribe(describe);
        book.setName(name);
        book.setPrice(Float.parseFloat(price));

        String message = null;
        try {
            BookRepo.getBookRepo().save(book);
            message = "提交信息保存成功";
        }catch (SQLException e) {
            e.printStackTrace();
            message = "提交信息保存失败!";
        }
        response.setContentType("text/html;charset=UTF-8");
        try(Writer writer = response.getWriter()){
            String html = "<center style='margin-top:5em'><h1>%s</h1><br><br>" +
                    "<a href = './submit-book.html'> 录入新图书 </a></center>";
            writer.write(String.format(html,message));
        }
    }
}
