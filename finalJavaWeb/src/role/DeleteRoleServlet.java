package role;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/api/deleteRole")
public class DeleteRoleServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String rolename = request.getParameter("rolename");
        Role role = null;
        try {
            role = RoleRepo.getRoleRepo().getByrolename(rolename);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            RoleRepo.getRoleRepo().deleteRoleByrolename(role.rolename);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.setContentType("application/json;charset=UTF-8");

        try(Writer writer=response.getWriter()){
            writer.write("{\"code\":200,\"message\":\"success\"}");
        }
    }

    private void writeJsonByJackson(Writer writer, List<Role> roles) throws IOException {
        String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(roles);
        writer.write(json);
    }
}
