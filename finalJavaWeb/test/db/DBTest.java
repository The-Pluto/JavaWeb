package db;

import role.Role;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBTest {
    public static void main(String[] args) throws SQLException {
        DBEngine engine = DBEngine.getGetInstance();
        String sql = "SELECT `id`, `name`, `author`, `price`, `describe` from book";

        List<Role> roles = engine.query(sql, new RecordVisitor<Role>() {
            @Override
            public Role visit(ResultSet rs) throws SQLException {
                Role role  = new Role();
                role.setRolename(rs.getString("rolename"));
                role.setStrength(rs.getFloat("strength"));
                role.setSkill(rs.getString("skill"));
                role.setDescribe(rs.getString("describe"));
                role.setPicture(rs.getString("picture"));
                return role;
            }
        });

        for (Role role:roles){
            System.out.println(role.toString());
        }
    }

}
