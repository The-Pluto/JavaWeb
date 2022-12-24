package swu.edu.cn.db.book;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteBook")
public class DeleteBookServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String id = request.getParameter("id");
        try{
            BookRepo.getBookRepo().deleteBookById(Long.valueOf(id));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        //重定向回原来的网页
        response.sendRedirect("./listBook");
    }

}
