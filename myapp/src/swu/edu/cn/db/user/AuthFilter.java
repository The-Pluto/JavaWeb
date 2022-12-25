package swu.edu.cn.db.user;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = request.getSession();
        System.out.println("auth filter");

        String uri = request.getRequestURI();
//        System.out.println("uri = " + uri);
        if(uri.endsWith("index.html") || uri.endsWith("/api/books") || uri.endsWith("login.html") ||
                uri.endsWith("/myapp/") || uri.endsWith("code") || uri.endsWith(".png") ||
                uri.endsWith("css") || uri.endsWith("login") || uri.endsWith("test") ||
                uri.endsWith("api/addToCart") || uri.endsWith("cart.html") || uri.endsWith("api/GetBooksinCart") ||
                uri.endsWith("api/removeFromCart")){
            filterChain.doFilter(request,response);
            return;
        }

        if(session == null){
//            System.out.println("auth failed");
            response.sendRedirect("./login.html");
        } else {
            Boolean toke = (Boolean) session.getAttribute(LoginServlet.LOGIN_TOKEN);
            if (toke == Boolean.TRUE){
//                System.out.println("登陆验证成功");
                filterChain.doFilter(request,response);
            }else{
//                System.out.println("auth failed");
                response.sendRedirect("./login.html");
            }
        }

    }
}
