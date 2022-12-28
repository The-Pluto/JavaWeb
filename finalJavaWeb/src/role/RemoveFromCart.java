package role;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/removeFromCart")
public class RemoveFromCart extends HttpServlet {

    private final static String CART = "cart";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String rolename = request.getParameter("rolename");
        List<String> cart = this.getCart(request);

        for(int i=0 ; i<cart.size() ; ++i){
            if(cart.get(i).equals(rolename)){
                cart.remove(i);
            }
        }
        response.setContentType("application/json;charset=UTF-8");

        try(Writer writer=response.getWriter()){
            writer.write("{\"code\":200,\"message\":\"success\"}");
        }
    }

    private List<String> getCart(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        List<String> cart = (List<String>) session.getAttribute(CART);
        if(cart == null){
            cart = new ArrayList<>();
            session.setAttribute(CART,cart);
        }
        return cart;
    }
}
