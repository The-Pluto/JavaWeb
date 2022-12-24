package swu.edu.cn.db.book;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

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

        Book book = null;
        try {
            book = this.getBookFromRequest(request,response);
        } catch (Exception e) {
            throw new IOException(e);
        }

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

    private Book getBookFromRequest(HttpServletRequest request,HttpServletResponse response) throws Exception {

        if(!ServletFileUpload.isMultipartContent(request)){
            return null;
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);

        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
        upload.setHeaderEncoding("UTF-8");

        String uploadPath = request.getServletContext().getRealPath(".") + File.separator + UPLOAD_DIRECTORY;
        System.out.println("uploadpath" + uploadPath);

        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()){
            uploadDir.mkdirs();
        }
        Book book = new Book();
        List<FileItem> formItems = upload.parseRequest(request);
        for(FileItem item:formItems){
            if(!item.isFormField()){
                String fileName = new File(item.getName()).getName();
                fileName = UUID.randomUUID().toString() +  fileName.substring(fileName.indexOf("."));
                String filePath = uploadPath + File.separator + fileName;

                File storeFile = new File(filePath);
                System.out.println(filePath);
                item.write(storeFile);
                book.setPicture(fileName);
            }
            else{
                String encoding = "UTF-8";
                if(item.getFieldName().equals("id")) {
                    String id = item.getString(encoding);
                    book.setId(Long.parseLong(id));
                }else if(item.getFieldName().equals("name")){
                    String name = item.getString(encoding);
                    book.setName(name);
                }else if(item.getFieldName().equals("author")){
                    String author = item.getString(encoding);
                    book.setAuthor(author);
                }else if(item.getFieldName().equals("price")){
                    String price = item.getString(encoding);
                    book.setPrice(Float.parseFloat(price));
                }else if(item.getFieldName().equals("id")){
                    String id = item.getString(encoding);
                    if(id != null){
                        book.setId(Long.parseLong(id));
                    }
                }else{
                    String describe = item.getString(encoding);
                    book.setDescribe(describe);
                }
            }
        }

        return book;
    }
}
