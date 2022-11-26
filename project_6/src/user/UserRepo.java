package src.user;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepo {

    public User getUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getString("id"));
        user.setAge(rs.getInt("age"));
        user.setName(rs.getString("name"));
        user.setSex(rs.getString("sex"));
        user.setHouseNumber(rs.getString("houseNumber"));
        return user;
    }



}
