package role;

import com.fasterxml.jackson.databind.ObjectMapper;

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

@WebServlet("/api/GetRolesinCart")
public class GetRolesInCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(true);
        List<String> cart = (List<String>) session.getAttribute(AddToCartServlet.CART);
        List<Role> result = new ArrayList<>();
        if(cart != null){
            try {
                result = RoleRepo.getRoleRepo().getByrolenames(cart);
            } catch (SQLException e) {
                throw new IOException(e.getMessage());
            }
        }

        resp.setContentType("application/json;charset=UTF-8");
        try(Writer writer = resp.getWriter()){
            this.writeJsonByJackson(writer,result);
        }
    }

    private void writeJsonByJackson(Writer writer, List<Role> roles) throws IOException {
        String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(roles);
        writer.write(json);
    }
}
