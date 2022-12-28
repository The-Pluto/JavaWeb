package user;

import java.sql.SQLException;
import java.util.List;

public class UserTest {
    public static void main(String[] args) throws SQLException {
//        User user = new User();
//        user.setId(UUID.randomUUID().toString());
//        user.setName("slc");
//        user.setUser("2206884457");
//        user.setPassword("Slc20030415");
//
//        UserRepo.getinstance().save(user);
//
//        User guest = new User();
//        guest.setId(UUID.randomUUID().toString());
//        guest.setUser("guest");
//        guest.setName("guest");
//        guest.setPassword("guest");
//        UserRepo.getinstance().save(guest);

        List<User> users = UserRepo.getinstance().getAll();
        for(User user:users){
            System.out.println(user.toString());
        }

    }
}
