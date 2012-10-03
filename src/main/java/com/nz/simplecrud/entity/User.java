package com.nz.simplecrud.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = User.ALL, query = "SELECT u FROM User u"),
    @NamedQuery(name = User.TOTAL, query = "SELECT COUNT(u) FROM User u")})
public class User implements Serializable {

    public final static String ALL = "User.populateUsers";
    public final static String TOTAL = "User.countUsersTotal";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;
    
    @Column(nullable = false, length = 50)
    private String username;
    
    @Column(length = 50)
    private String firstname;
    
    @Column(length = 50)
    private String lastname;
    
    @Column(length = 50)
    private String email;
    
    @Column(length = 64)
    private String password;
    
    @OneToOne(cascade = {CascadeType.ALL})
    private Address address;
    
    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = {
        @JoinColumn(name = "User_userid")}, inverseJoinColumns = {
        @JoinColumn(name = "Role_roleid")})
    private List<Role> roles;

    public User() {
        roles = new ArrayList<Role>();
        address = new Address();
    }

    public Integer getUserid() {
        return this.userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        return userid != null ? this.getClass().hashCode() + userid.hashCode() : super.hashCode();
    }
    
    @Override
     public boolean equals(Object obj) {
          if (obj == null)
               return false;
          else if (!(obj instanceof User))
               return false;
          else if (((User) obj).userid.equals(
                    this.userid))
               return true;
          else
               return false;
     }
    
    @Override
    public String toString() {
        return "com.nz.simplecrud.entity.User[ id=" + userid + " username=" + userid + " ]";
    }
}