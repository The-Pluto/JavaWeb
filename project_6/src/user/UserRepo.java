package src.user;

import src.db.DBEngine;
import src.db.RecordVisitor;
import src.house.HouseRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepo {
    private static UserRepo instance = new UserRepo();

    public static UserRepo getInstance() {
        return instance;
    }

    public void save(String username, String password) throws SQLException {
        User user = new User();
        String template = "INSERT INTO `user`(`username`, `password`) VALUES " +
                "('%s', MD5('%s'))";
        String sql = String.format(template,username,password);
        DBEngine.getInstance().execute(sql);
    }

    public User userAuth(String username) throws SQLException {
        String template = "SELECT * FROM user WHERE `username`='%s'";
        String sql = String.format(template,username);
//        System.out.println(sql);
        List<User> users = DBEngine.getInstance().query(sql, new RecordVisitor<User>() {
            @Override
            public User visit(ResultSet rs) throws SQLException {
                return UserRepo.getInstance().getUserFromResultSet(rs);
            }
        });
        return users.size() == 0 ? null : users.get(0);
    }
    public User getUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getString("id"));
        user.setAge(rs.getInt("age"));
        user.setName(rs.getString("name"));
        user.setSex(rs.getString("sex"));
        user.setHouseNumber(rs.getString("houseNumber"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setStatus(rs.getString("status"));
        return user;
    }

    public List<User> getAllUsers() throws SQLException {
        String sql = "SELECT * FROM `user`";
        List<User> users = new ArrayList<>();
        users = DBEngine.getInstance().query(sql, new RecordVisitor<User>() {
            @Override
            public User visit(ResultSet rs) throws SQLException {
                return UserRepo.getInstance().getUserFromResultSet(rs);
            }
        });
        return users;
    }

    public void DeleteByUserName(String username) throws SQLException {
        String template = "DELETE FROM `user` WHERE `username` = '%s'";
        String sql = String.format(template,username);
        DBEngine.getInstance().execute(sql);
        System.out.println(sql);
    }

    public User getUserByUsername(String username) throws SQLException {
        String template = "SELECT * FROM `user` WHERE `username` = %s";
        String sql = String.format(template,username);
        List<User> users = DBEngine.getInstance().query(sql, new RecordVisitor<User>() {
            @Override
            public User visit(ResultSet rs) throws SQLException {
                return UserRepo.getInstance().getUserFromResultSet(rs);
            }
        });
        return users.size() == 0 ? null : users.get(0);
    }
}
