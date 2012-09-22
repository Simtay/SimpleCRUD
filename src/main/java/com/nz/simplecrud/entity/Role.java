package com.nz.simplecrud.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@NamedQueries({@NamedQuery(name = Role.ALL, query = "SELECT r FROM Role r")})
public class Role implements Serializable {

    public final static String ALL = "Role.populateRoles";
    @Id
    private Integer roleid;
    private String roledesc;
    private String rolename;

    public Role() {
    }

    public Role(Integer roleid, String rolename) {
        this.roleid = roleid;
        this.rolename = rolename;
    }

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Integer getRoleid() {
        return this.roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRoledesc() {
        return this.roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }

    public String getRolename() {
        return this.rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    @Override
    public int hashCode() {
        return roleid != null ? this.getClass().hashCode() + roleid.hashCode() : super.hashCode();
    }
    
    @Override
     public boolean equals(Object obj) {
          if (obj == null)
               return false;
          else if (!(obj instanceof Role))
               return false;
          else if (((Role) obj).roleid.equals(
                    this.roleid))
               return true;
          else
               return false;
     }
    
}