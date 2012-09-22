package com.nz.simplecrud.controller;

import com.nz.simplecrud.entity.Role;
import com.nz.simplecrud.entity.User;
import com.nz.simplecrud.service.DataAccessService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import org.primefaces.model.LazyDataModel;




@ManagedBean
@ViewScoped
public class UserController implements Serializable  {
	
        private @Inject DataAccessService das;
        private User[] selectedUsers; 
        private LazyDataModel<User> lazyModel; 
        private User newUser = new User();
        private User selectedUser = new User();
        private List<Role> roleList; 

        
	public UserController() {
            
	}
        
        @PostConstruct
        public void init(){
            lazyModel = new LazyUserDataModel(das);
            roleList = das.findWithNamedQuery(Role.ALL);
        }
      
        public void doCreateUser() {
            das.create(newUser);
        }
        
        public void doUpdateUser(ActionEvent actionEvent){
            das.update(selectedUser);
        }
        
        public void doDeleteUsers(ActionEvent actionEvent){
            das.deleteItems(selectedUsers);
        }     
        
        /*
         * Getters, Setters
         */
        
        public User getSelectedUser() {  
            return selectedUser;  
        }  
        
        public void setSelectedUser(User selectedUser) {  
            this.selectedUser = selectedUser;  
        } 
        
        public User[] getSelectedUsers() {  
            return selectedUsers;  
        }  
        
        public void setSelectedUsers(User[] selectedUsers) {  
            this.selectedUsers = selectedUsers;  
        }

        public User getNewUser() {
            return newUser;
        }

        public void setNewUser(User newUser) {
            this.newUser = newUser;
        }
       
        public LazyDataModel<User> getLazyModel() {
            return lazyModel;
        }

        public List<Role> getRoleList() {
            return roleList;
        }

        public void setRoleList(List<Role> roleList) {
            this.roleList = roleList;
        }
}
                    