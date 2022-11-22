package swu.edu.cn.db.user;

import swu.edu.cn.db.db.DBEngine;
import swu.edu.cn.db.db.RecordVisitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//´æ´¢
public class UserRepo {
    private static UserRepo instance = new UserRepo();

    public static UserRepo getinstance(){
        return instance;
    }

    public void save(User user) throws SQLException {
        String template = "INSERT INTO `user`(`id`, `name`, `user`, `password`) " +
                "VALUES ('%s','%s','%s',MD5('%s'))";
        String sql = String.format(template,user.getId(),user.getName(),user.getUser(),user.getPassword());
        DBEngine.getGetInstance().execute(sql);


    }

    public void deleteByUser(User user) throws SQLException {
        String template = "DELETE FROM 'user' WHERE 'id' = '%s'";
        DBEngine.getGetInstance().execute(String.format(template, user.getId()));
    }

    public void deleteByName(User user) throws SQLException {
        String template = "DELETE FROM 'user' WHERE 'name' = '%s'";
        DBEngine.getGetInstance().execute(String.format(template, user.getId()));
    }

    public List<User> getAll() throws SQLException {
        String sql = "SELECT `id`,`name`,`user`,`password` FROM `user`";
        return DBEngine.getGetInstance().query(sql, new RecordVisitor<User>() {
            @Override
            public User visit(ResultSet rs) throws SQLException {
                User user = new User();
                user.setUser(rs.getString("user"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setId(rs.getString("id"));
                return user;
            }
        });
    }
}
