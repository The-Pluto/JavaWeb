package swu.edu.cn.db.book;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/GetBooksinCart")
public class GetBooksinCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(true);
        List<Long> cart = (List<Long>) session.getAttribute(AddToCartServlet.CART);
        List<Book> result = new ArrayList<>();
        if(cart != null){
            try {
                result = BookRepo.getBookRepo().getByIds(cart);
            } catch (SQLException e) {
                throw new IOException(e.getMessage());
            }
        }

        resp.setContentType("application/json;charset=UTF-8");
        try(Writer writer = resp.getWriter()){
            this.writeJsonByJackson(writer,result);
        }
    }

    private void writeJsonByJackson(Writer writer, List<Book> books) throws IOException {
        String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(books);
        writer.write(json);
    }
}
