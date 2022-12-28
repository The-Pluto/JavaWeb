package role;


import db.DBEngine;
import db.RecordVisitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoleRepo {

    private final static RoleRepo instance = new RoleRepo();

    public static RoleRepo getRoleRepo(){
        return instance;
    }

    public void SaveRole(Role role) throws SQLException {
        Role role1 = RoleRepo.getRoleRepo().getByrolename(role.rolename);
        if(role1 == null){
            this.InsertRole(role);
        }
        else{
            this.editRole(role);
        }
    }

    public void InsertRole(Role role) throws SQLException {
        String template = "INSERT INTO `role`(`rolename`, `strength`, `country`, `skill`, `describe`, `picture`)" +
                "VALUES('%s', %s, '%s', '%s', '%s', '%s')";
        String sql = String.format(template,role.getRolename(),role.getStrength(),role.getCountry(),role.getSkill(),role.getDescribe(),role.getPicture());
        System.out.println(sql);
        DBEngine.getGetInstance().execute(sql);
    }

    public void editRole(Role role) throws SQLException {
        String template = "UPDATE `role` SET `strength` = %s, `country` = \"%s\", `skill` = \"%s\"" +
                ", `describe` = \"%s\", `picture` = \"%s\"" +
                "WHERE `rolename` = \"%s\"";
        String sql = String.format(template,role.getStrength(),role.getCountry(),role.getSkill(),role.getDescribe(),role.getPicture(),role.getRolename());
        System.out.println(sql);
        DBEngine.getGetInstance().execute(sql);
    }
    public List<Role> getAllRole() throws SQLException {
        String sql = "SELECT * FROM role";
        return DBEngine.getGetInstance().query(sql, new RecordVisitor<Role>() {
            @Override
            public Role visit(ResultSet rs) throws SQLException {
                return RoleRepo.getRoleRepo().getroleFromResultSet(rs);
            }
        });
    }

    public void deleteRoleByrolename(String rolename) throws SQLException {
        String template = "DELETE FROM `role` WHERE `rolename` = \"%s\"";
        DBEngine.getGetInstance().execute(String.format(template,rolename));
    }

    public Role getByrolename(String rolename) throws SQLException {
        String template = "SELECT * FROM `role` WHERE `rolename` = \"%s\"";
        String sql = String.format(template,rolename);
        List<Role> roles = DBEngine.getGetInstance().query(sql, new RecordVisitor<Role>() {
            @Override
            public Role visit(ResultSet rs) throws SQLException {
                return RoleRepo.getRoleRepo().getroleFromResultSet(rs);
            }
        });
        return roles.size()==0 ? null : roles.get(0);
    }

    public List<Role> getByrolenames(List<Long> rolenames) throws SQLException {
        String template = "SELECT * FROM `role` WHERE `rolename` IN (%s) ";
        String names = "";
        for(int i=0 ; i<rolenames.size() ; ++i){
            names += ((i>0) ? "," : "") + rolenames.get(i);
        }
        String sql = String.format(template,names);
        List<Role> roles = DBEngine.getGetInstance().query(sql, new RecordVisitor<Role>() {
            @Override
            public Role visit(ResultSet rs) throws SQLException {
                return RoleRepo.getRoleRepo().getroleFromResultSet(rs);
            }
        }) ;
        return roles;
    }

    private Role getroleFromResultSet(ResultSet rs) throws SQLException {
        Role role=new Role();
        role.setRolename(rs.getString("rolename"));
        role.setCountry(rs.getString("country"));
        role.setStrength(rs.getFloat("strength"));
        role.setDescribe(rs.getString("describe"));
        role.setSkill(rs.getString("skill"));
        role.setPicture(rs.getString("picture"));
        return role;
    }
}
