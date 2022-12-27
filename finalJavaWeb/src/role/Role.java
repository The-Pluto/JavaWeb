package role;

public class Role {
    String rolename;
    float strength;
    String country;
    String skill;
    String describe;
    String picture;

    @Override
    public String toString() {
        return "Role{" +
                "rolename='" + rolename + '\'' +
                ", strength='" + strength + '\'' +
                ", country='" + country + '\'' +
                ", skill='" + skill + '\'' +
                ", describe='" + describe + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }

    public Role() {
    }

    public Role(String rolename, float strength, String country, String skill, String describe, String picture) {
        this.rolename = rolename;
        this.strength = strength;
        this.country = country;
        this.skill = skill;
        this.describe = describe;
        this.picture = picture;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setPicture(String picture){
        this.picture = picture;
    }

    public String getRolename() {
        return rolename;
    }

    public float getStrength() {
        return strength;
    }

    public String getCountry() {
        return country;
    }

    public String getSkill() {
        return skill;
    }

    public String getDescribe() {
        return describe;
    }

    public String getPicture(){
        return picture;
    }
}
