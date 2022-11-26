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
//        ������ɾ�����Ĳ���
//        String template = "INSERT INTO `user`(`name`,`age`,`sex`,`id`,`username`,`password`,`status`)" +
//                "VALUES ('%s', %s, '%s', '%s', '%s', '%s','%s')";
//        String sql = String.format(template,"������",20,"��","330324200304150818","2206884457","Slc20030415","����Ա");
//        System.out.println(sql);
//        DBEngine.getInstance().execute(sql);

//        ���ǸĲ�����Ĳ���
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
