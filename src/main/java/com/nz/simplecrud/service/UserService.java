package com.nz.simplecrud.service;

import com.nz.simplecrud.entity.User;
import javax.ejb.Stateless;

/**
 *
 * @author Emre Simtay <emre@simtay.com>
 */

@Stateless
public class UserService extends DataAccessService<User>{
    
    public UserService(){
        super(User.class);
    }   
}
