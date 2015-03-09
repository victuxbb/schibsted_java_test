/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.victuxbb.javatest.model.user;

import com.victuxbb.javatest.model.role.RoleEnum;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victux
 */
public class InMemoryUserRepository implements UserRepositoryInterface
{

    private List<User> users;
    
    
    public InMemoryUserRepository() 
    {
        this.users = new ArrayList<User>();
        
        User user_pag_1 = new User(1,"usuario_pag_1","pag_1",RoleEnum.PAG_1);
        User user_pag_2 = new User(1,"usuario_pag_2","pag_2",RoleEnum.PAG_2);
        User user_pag_3 = new User(1,"usuario_pag_3","pag_3",RoleEnum.PAG_3);
        
        User user_pag_4 = new User(1,"usuario_pag_12","pag_12",RoleEnum.PAG_1);
        user_pag_4.addRole(RoleEnum.PAG_2);
        
        this.users.add(user_pag_1);
        this.users.add(user_pag_2);
        this.users.add(user_pag_3);
        this.users.add(user_pag_4);
        
    }    
    
    
    public User findUserByUsernameAndPassword(String username,String password) throws UserNotFoundException
    {
        for (User user : this.users) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) return user;            
        }
        throw new UserNotFoundException("User not found!");
    }
    
}
