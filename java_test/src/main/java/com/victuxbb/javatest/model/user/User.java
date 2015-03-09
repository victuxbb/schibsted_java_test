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
public class User 
{
    private int id;
    private String username;
    private String password;
    private List<RoleEnum> roles;

    public User(int id, String username, String password, RoleEnum role) {
        this.roles = new ArrayList();
        this.id = id;
        this.username = username;
        this.password = password;
        this.addRole(role);
        
        
    }   
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEnum> roles) {
        this.roles = roles;
    }
    public void addRole(RoleEnum role)
    {
        this.roles.add(role);
    }
    
    
    
    
}
