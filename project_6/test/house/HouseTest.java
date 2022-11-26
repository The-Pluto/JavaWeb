package test.house;

import src.db.DBEngine;

import java.sql.SQLException;

public class HouseTest {
    public static void main(String[] args) throws SQLException {
        String template = "INSERT INTO `house`(`price`,`size`,`housesCount`,`houseNumber`)" +
                "VALUES (%s, %s, %s, '%s')";
        String sql = String.format(template,45,102,3,"102");
        System.out.println(sql);
        DBEngine.getInstance().execute(sql);
    }

}
