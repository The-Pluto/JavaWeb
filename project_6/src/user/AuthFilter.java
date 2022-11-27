package src.user;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter extends HttpFilter {

    public static final String URI = "URI";

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = request.getSession(Boolean.FALSE);
        System.out.println("auth filter");

        String uri = request.getRequestURI();
        System.out.println("uri = " + uri);
        if(uri.endsWith("index.html") || uri.endsWith("login.html") || uri.endsWith("/project_6/") || uri.endsWith("code") ||
                uri.endsWith(".register") || uri.endsWith("logout") || uri.endsWith("authLimit") ||
                uri.endsWith(".png") || uri.endsWith("css") || uri.endsWith("login")){
            filterChain.doFilter(request,response);
            return;
        }

        if(session == null){
            System.out.println("auth failed");
            response.sendRedirect("./login.html");
        } else {
            String status = String.valueOf(session.getAttribute(LoginServlet.LOGIN_TOKEN));
            if (status.equals("admin")){
                if(!uri.endsWith("user.html") || !uri.endsWith("register") || !uri.endsWith("editUser")){
                    filterChain.doFilter(request,response);
                }
            } else if (status.equals("guest")) {
                if(uri.endsWith("user.html") || uri.endsWith("register") || uri.endsWith("editUser")
                        || uri.endsWith("listHouse") || uri.endsWith("editInfo")){
                    session.setAttribute(AuthFilter.URI,uri);
                    filterChain.doFilter(request,response);
                }
                else{
                    response.sendRedirect("./authLimit");
                }
            } else{
//                System.out.println("auth failed");
                response.sendRedirect("./login.html");
            }
        }
    }
}
