package test.db;

import src.db.DBEngine;
import src.db.RecordVisitor;
import src.house.House;
import src.house.HouseRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBTest {
    public static void main(String[] args) throws SQLException {
//        这是增删操作的测试
//        String template = "INSERT INTO `house`(`owner`,`price`,`size`,`housesCount`)" +
//                "VALUES ('%s', %s, %s, %s)";
//        String sql = String.format(template,"邵梁铖2号",45.3,100,4);
//        System.out.println(sql);
//        DBEngine.getInstance().execute(sql);

//        这是改查操作的测试
        String sql = "SELECT * FROM `house`";
        List<House> houses = DBEngine.getInstance().query(sql, new RecordVisitor<House>() {
            @Override
            public House visit(ResultSet rs) throws SQLException {
                return HouseRepo.getInstance().getHouseFromResultSet(rs);
            }
        });

        for(House house:houses){
            System.out.println(house.toString());
        }
    }
}



