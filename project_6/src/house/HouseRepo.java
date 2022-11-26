package src.house;

import src.db.DBEngine;
import src.db.RecordVisitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HouseRepo {
    private static HouseRepo instance = new HouseRepo();

    public static HouseRepo getInstance() {
        return instance;
    }

    public void HouseAdd(float price,float size,int housesCount,String houseNumber) throws SQLException {
        String template = "INSERT INTO `house`(`price`,`size`,`housesCount`,`houseNumber`)" +
                "VALUES (%s, %s, %s, '%s')";
        String sql = String.format(template,price,size,housesCount,houseNumber);
        System.out.println(sql);
        DBEngine.getInstance().execute(sql);
    }

    public House getHouseFromResultSet(ResultSet rs) throws SQLException {
        House house = new House();
        house.setOwner(rs.getString("owner"));
        house.setPrice(rs.getFloat("price"));
        house.setHousesCount(rs.getInt("housesCount"));
        house.setHouseNumber(rs.getString("houseNumber"));
        house.setSize(rs.getFloat("size"));
        return house;
    }

    public List<House> GetAllHouse() throws SQLException {
        String sql = "SELECT * FROM `house`";
        List<House> houses = DBEngine.getInstance().query(sql, new RecordVisitor<House>() {
            @Override
            public House visit(ResultSet rs) throws SQLException {
                return HouseRepo.getInstance().getHouseFromResultSet(rs);
            }
        });
        return houses;
    }

    public House GetByHouseNumber(String houseNumber) throws SQLException {
        String template = "SELECT * FROM `house` WHERE `houseNumber`='%s'";
        String sql = String.format(template,houseNumber);
        List<House> houses = DBEngine.getInstance().query(sql, new RecordVisitor<House>() {
            @Override
            public House visit(ResultSet rs) throws SQLException {
                return HouseRepo.getInstance().getHouseFromResultSet(rs);
            }
        });
        return houses.size()==0 ? null : houses.get(0);
    }

    public void EditByHouseNumber(House house) throws SQLException {
        String template = "UPDATE `house` SET `price`=%s,`size`=%s,`housesCount`=%s WHERE `houseNumber` = '%s'";
        String sql = String.format(template, house.getPrice(),house.getSize(),house.getHousesCount(),house.getHouseNumber());
        System.out.println(sql);
        DBEngine.getInstance().execute(sql);
    }

    public void DeleteByHouseNumber(String houseNumber) throws SQLException {
        String template = "DELETE FROM `house` WHERE `houseNumber`='%s'";
        String sql = String.format(template,houseNumber);
        System.out.println(sql);
        DBEngine.getInstance().execute(sql);
    }


}
