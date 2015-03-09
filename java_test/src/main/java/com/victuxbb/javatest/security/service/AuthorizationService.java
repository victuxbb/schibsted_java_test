/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.victuxbb.javatest.security.service;

import com.victuxbb.javatest.model.role.RoleEnum;
import com.victuxbb.javatest.model.user.User;
import com.victuxbb.javatest.security.token.UserToken;
import com.victuxbb.javatest.security.token.UserTokenNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 *
 * @author victux
 */
public class AuthorizationService 
{
    private static AuthorizationService instance;
    
    public boolean isAuthorized(String uri,UserToken userToken)
    {        
        String pattern = "(pag_[0-9]).jsp*";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(uri);
        String page = "";
        if (m.find()) {
            page = m.group(1);
        }
        
        boolean authorized = false;
        User user = userToken.getUser();
        for (RoleEnum role : user.getRoles()) {
            if( role.name().toLowerCase().equals(page) ) {
                authorized = true;
            }
        }
        return authorized;           
      
    }
    
    public static AuthorizationService getInstance()
    {
        if(null == instance){
            instance = new AuthorizationService();
        }
        return instance;
    }

    
}
