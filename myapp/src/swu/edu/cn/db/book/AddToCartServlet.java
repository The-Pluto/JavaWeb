package swu.edu.cn.db.book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/addToCart")
public class AddToCartServlet extends HttpServlet {

    public final static String CART = "cart";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String bookId = request.getParameter("id");
        response.setContentType("application/json;charset=UTF-8");
        List<Long> cart = this.getCart(request, response);
        cart.add(Long.valueOf(bookId));

        for(Long id:cart){
            System.out.println("图书id:" + bookId);
        }

        try(Writer writer=response.getWriter()){
            writer.write("{\"code\":200,\"message\":\"success\"}");
        }
    }

    private List<Long> getCart(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession(true);
        List<Long> cart = (List<Long>) session.getAttribute(CART);
        if(cart == null){
            cart = new ArrayList<>();
            session.setAttribute(CART,cart);
        }
        return cart;
    }
}
