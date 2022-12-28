package role;


import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/api/roles")
public class listRoleApi extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<Role> roles = RoleRepo.getRoleRepo().getAllRole();
            response.setContentType("application/json;charset=UTF-8");
            try(Writer writer = response.getWriter()){
                this.writeJsonByJackson(writer,roles);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeJsonByJackson(Writer writer,List<Role> roles) throws IOException {
        String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(roles);
        writer.write(json);
    }
}
