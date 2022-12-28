package role;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@WebServlet("/submit")
public class SaveRoleServlet extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "upload";
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Role role = null;
        try {
            role = this.getBookFromRequest(request,response);
        } catch (Exception e) {
            throw new IOException(e);
        }
        System.out.println(role);
        String message = null;
        try {
            RoleRepo.getRoleRepo().SaveRole(role);
            message = "提交信息保存成功";
        }catch (SQLException e) {
            e.printStackTrace();
            message = "提交信息保存失败!";
        }
        System.out.println(message);

        response.setContentType("text/html;charset=UTF-8");
        try(Writer writer = response.getWriter()){
            String html = "<center style='margin-top:5em'><h1>%s</h1><br><br>" +
                    "<a href = './addrole.html'> 添加新武将 </a>" +
                    "<a href = './admin.html'> 返回管理者页面 </a>" +
                    "<a href = './index.html'> 返回主页 </a> </center>";

            writer.write(String.format(html,message));
        }
    }


    private Role getBookFromRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
        Role role = new Role();
        List<FileItem> formItems = upload.parseRequest(request);
        for(FileItem item:formItems){
            if(!item.isFormField()){
                String fileName = new File(item.getName()).getName();
                fileName = UUID.randomUUID().toString() +  fileName.substring(fileName.indexOf("."));
                String filePath = uploadPath + File.separator + fileName;

                File storeFile = new File(filePath);
                System.out.println(filePath);
                item.write(storeFile);
                role.setPicture(fileName);
            }
            else{
                String encoding = "UTF-8";
                if(item.getFieldName().equals("rolename")) {
                    String id = item.getString(encoding);
                    role.setRolename(id);
                }else if(item.getFieldName().equals("skill")){
                    String skill = item.getString(encoding);
                    role.setSkill(skill);
                }else if(item.getFieldName().equals("describe")){
                    String describe = item.getString(encoding);
                    role.setDescribe(describe);
                }else if(item.getFieldName().equals("strength")){
                    String strength = item.getString(encoding);
                    role.setStrength(Float.parseFloat(strength));
                }else if(item.getFieldName().equals("country")) {
                    String country = item.getString(encoding);
                    role.setCountry(country);
                }
            }
        }
        return role;
    }
}
