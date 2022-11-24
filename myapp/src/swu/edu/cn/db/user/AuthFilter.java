package swu.edu.cn.db.user;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = request.getSession();
        System.out.println("auth filter");

        String uri = request.getRequestURI();
        if(uri.endsWith("index.html") || uri.endsWith("login.html") ||
                uri.endsWith(".png") || uri.endsWith("css") || uri.endsWith("login")){
            filterChain.doFilter(request,response);
            return;
        }

        if(session == null){
            System.out.println("auth failed");
            response.sendRedirect("./login.html");
        } else {
            Boolean toke = (Boolean) session.getAttribute(LoginServlet.LOGIN_TOKEN);
            if (toke == Boolean.TRUE){
                System.out.println("登陆验证成功");
                filterChain.doFilter(request,response);
            }else{
                System.out.println("auth failed");
                response.sendRedirect("./login.html");
            }
        }

    }
}
