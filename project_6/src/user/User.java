package src.user;

public class User {
    String name;
    int age;
    String sex;
    String id;
    String houseNumber;

    public User() {
    }

    public User(String name, int age, String sex, String id, String houseNumber) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.id = id;
        this.houseNumber = houseNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getId() {
        return id;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", id='" + id + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                '}';
    }
}
