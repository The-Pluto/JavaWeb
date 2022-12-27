package user;

public class User {
    public String username;
    public String password;
    public String UUID;

    public String user;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", UUID='" + UUID + '\'' +
                ", user='" + user + '\'' +
                '}';
    }

    public User() {
    }

    public User(String username, String password, String UUID, String user) {
        this.username = username;
        this.password = password;
        this.UUID = UUID;
        this.user = user;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUUID() {
        return UUID;
    }

    public String getUser() {
        return user;
    }
}
