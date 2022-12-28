package user;

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
        if(uri.endsWith("index.html") ||
                uri.endsWith("login.html") ||
                uri.endsWith("detail.html") ||
                uri.endsWith("addToCart") ||
                uri.endsWith("getback") ||
                uri.endsWith("login") ||
                uri.endsWith("SignUp") ||
                uri.endsWith("forget.html") ||
                uri.endsWith("code") ||
                uri.endsWith("removeFromCart") ||
                uri.endsWith("GetRolesinCart") ||
                uri.endsWith("cart.html") ||
                uri.endsWith("forgetpassword.html") ||
                uri.endsWith("signup.html") ||
                uri.endsWith(".png") ||
                uri.endsWith(".jpg") ||
                uri.endsWith(".jfif") ||
                uri.endsWith("css") ||
                uri.endsWith("test") ||
                uri.endsWith("roles") ||
                uri.endsWith("edit") ||
                uri.endsWith("/finalJavaWeb/")){
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