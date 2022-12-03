package swu.edu.cn.db.book;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.SQLException;

@WebServlet("/saveBook")
public class SaveBookServlet extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "upload";
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String id = request.getParameter("id");
//        String name = request.getParameter("name");
//        String author = request.getParameter("author");
//        String describe = request.getParameter("describe");
//        String price = request.getParameter("price");
//
//
//        Book book = new Book();
//        book.setAuthor(author);
//        book.setDescribe(describe);
//        book.setName(name);
//        book.setPrice(Float.parseFloat(price));
//
//        if(id != null){
//            book.setId(Long.parseLong(id));
//        }

        Book book = this.getBookFromRequest(request,response);

        String message = null;
        try {
            BookRepo.getBookRepo().saveBook(book);
            message = "提交信息保存成功";
        }catch (SQLException e) {
            e.printStackTrace();
            message = "提交信息保存失败!";
        }
        response.setContentType("text/html;charset=UTF-8");
        try(Writer writer = response.getWriter()){
            String html = "<center style='margin-top:5em'><h1>%s</h1><br><br>" +
                    "<a href = './submit-book.html'> 录入新图书 </a>" +
                    "<a href = './listBook'> 显示列表 </a>" +
                    "<a href = './index.html'> 返回主页 </a> </center>";
            writer.write(String.format(html,message));
        }
    }

    private Book getBookFromRequest(HttpServletRequest request,HttpServletResponse response){
        Book book = new Book();
        if(!ServletFileUpload.isMultipartContent(request)){
            return null;
        }

        return book;
    }
}
