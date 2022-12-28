package user;

import db.DBEngine;
import db.RecordVisitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//存储
public class UserRepo {
    private final static UserRepo instance = new UserRepo();

    public static UserRepo getinstance(){
        return instance;
    }

    public void save(User user) throws SQLException {
        String template = "INSERT INTO `user`(`user`, `username`, `password`, `UUID`) " +
                "VALUES ('%s','%s',MD5('%s'),'%s')";
        String sql = String.format(template,user.getUser(),user.getUsername(),user.getPassword(),user.getUUID());
        DBEngine.getGetInstance().execute(sql);
        System.out.println(sql);
    }

    public User auth(String user, String password) throws SQLException {
        String sql = "SELECT * FROM `user` WHERE `username` = '%s' AND `password` = MD5('%s')";
        List<User> users =  DBEngine.getGetInstance().query(
                String.format(sql, user, password), new RecordVisitor<User>() {
                    @Override
                    public User visit(ResultSet rs) throws SQLException {
                        return UserRepo.instance.getUserFromResultSet(rs);
                    }
                });
        return users.size() == 0 ? null : users.get(0);

    }

    public User findByUUID(String UUID) throws SQLException {
        String sql = "SELECT * FROM `user` WHERE `UUID` = '%s'";
        List<User> users =  DBEngine.getGetInstance().query(
                String.format(sql, UUID), new RecordVisitor<User>() {
                    @Override
                    public User visit(ResultSet rs) throws SQLException {
                        return UserRepo.instance.getUserFromResultSet(rs);
                    }
                });
        return users.size() == 0 ? null : users.get(0);
    }

    public void deleteByUser(User user) throws SQLException {
        String template = "DELETE FROM `user` WHERE `username` = '%s' AND `password` = MD5('%s')";
        DBEngine.getGetInstance().execute(String.format(template, user.getUsername(), user.getPassword()));
    }

    public void deleteByName(User user) throws SQLException {
        String template = "DELETE FROM `user` WHERE 'user' = '%s'";
        DBEngine.getGetInstance().execute(String.format(template, user.getUser()));
    }

    public List<User> getAll() throws SQLException {
        String sql = "SELECT * FROM `user`";
        return DBEngine.getGetInstance().query(sql, new RecordVisitor<User>() {
            @Override
            public User visit(ResultSet rs) throws SQLException {
                return UserRepo.getinstance().getUserFromResultSet(rs);
            }
        });
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUser(rs.getString("user"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setUUID(rs.getString("UUID"));
        return user;
    }

    public void changePassword(User user) throws SQLException {
        String template = "UPDATE `user` SET `password` = MD5('%s') WHERE `UUID` = \"%s\"";
        String sql = String.format(template,"123456",user.getUUID());
        DBEngine.getGetInstance().execute(sql);
    }
}