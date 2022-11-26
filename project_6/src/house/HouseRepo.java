package src.house;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HouseRepo {
    private static HouseRepo instance = new HouseRepo();

    public static HouseRepo getInstance() {
        return instance;
    }

    public House getHouseFromResultSet(ResultSet rs) throws SQLException {
        House house = new House();
        house.setOwner(rs.getString("owner"));
        house.setPrice(rs.getFloat("price"));
        house.setSize(rs.getFloat("size"));
        house.setHousesCount(rs.getInt("housesCount"));
        return house;
    }
}
