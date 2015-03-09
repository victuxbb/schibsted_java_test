/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.victuxbb.javatest.security.token;

import com.victuxbb.javatest.model.user.User;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author victux
 */
public class UserToken 
{
    private String uuid;
    private Date expires;
    private User user;

    public UserToken(User user) {
        this.uuid = UUID.randomUUID().toString();       
        this.user = user;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    
    
}
