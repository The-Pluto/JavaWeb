package role;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

@WebServlet("/api/edit")
public class EditRoleApi extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String rolename = request.getParameter("rolename");
        Role role = null;
        try {
            role = RoleRepo.getRoleRepo().getByrolename(rolename);
            response.setContentType("application/json;charset=UTF-8");
            try(Writer writer = response.getWriter()){
                this.writeJsonByJackson(writer,role);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeJsonByJackson(Writer writer, Role role) throws IOException {
        String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(role);
        writer.write(json);
    }
}
