package test.user;

import src.db.DBEngine;
import src.db.RecordVisitor;
import src.house.House;
import src.house.HouseRepo;
import src.user.User;
import src.user.UserRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserTest {
    public static void main(String[] args) throws SQLException {
//        这是增删操作的测试
//        String template = "INSERT INTO `user`(`name`,`age`,`sex`,`id`,`username`,`password`,`status`)" +
//                "VALUES ('%s', %s, '%s', '%s', '%s', '%s','%s')";
//        String sql = String.format(template,"邵梁铖",20,"男","330324200304150818","2206884457","Slc20030415","管理员");
//        System.out.println(sql);
//        DBEngine.getInstance().execute(sql);

//        这是改查操作的测试
        String sql = "SELECT * FROM `user`";
        List<User> users = DBEngine.getInstance().query(sql, new RecordVisitor<User>() {
            @Override
            public User visit(ResultSet rs) throws SQLException {
                return UserRepo.getInstance().getUserFromResultSet(rs);
            }
        });

        for(User user:users){
            System.out.println(user.toString());
        }

    }
}
