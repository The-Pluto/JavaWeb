package src.user;

import src.house.HouseRepo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepo {
    private static UserRepo instance = new UserRepo();

    public static UserRepo getInstance() {
        return instance;
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



}
