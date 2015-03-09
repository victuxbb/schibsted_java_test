/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.victuxbb.javatest.security.service;

import com.victuxbb.javatest.model.user.User;
import com.victuxbb.javatest.security.token.InMemoryUserTokenRepository;
import com.victuxbb.javatest.security.token.UserToken;
import java.util.Calendar;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author victux
 */
public class AuthenticationService
{
    public static int SESSION_TIME = 5;
    public static final String SESSION_COOKIE_NAME="tokenid";
    private static AuthenticationService instance;
    
    public void createSession(User user, HttpServletResponse response)
    {
        UserToken userToken = new UserToken(user);
        this.setExpires(userToken);
        
        Cookie c = new Cookie(AuthenticationService.SESSION_COOKIE_NAME,userToken.getUuid());
        c.setMaxAge((int)(userToken.getExpires().getTime()-System.currentTimeMillis())/1000);
        c.setPath("/");
        response.addCookie(c);
        
        InMemoryUserTokenRepository userTokenRepository = InMemoryUserTokenRepository.getInstance();
        userTokenRepository.save(userToken);
        
    }
    
    public void refreshSession(UserToken userToken,HttpServletResponse response)
    {
        this.setExpires(userToken);
        Cookie c = new Cookie(AuthenticationService.SESSION_COOKIE_NAME,userToken.getUuid());                
        c.setMaxAge(AuthenticationService.SESSION_TIME*60);        
        c.setPath("/");
        response.addCookie(c);
        
        
        InMemoryUserTokenRepository userTokenRepository = InMemoryUserTokenRepository.getInstance();
        userTokenRepository.update(userToken);
    }
    
    public void removeSession(UserToken userToken, HttpServletResponse response)
    {
        InMemoryUserTokenRepository userTokenRepository = InMemoryUserTokenRepository.getInstance();
        UserTokenService userTokenService = UserTokenService.getInstance();
        Cookie cookie = new Cookie(AuthenticationService.SESSION_COOKIE_NAME,userToken.getUuid());        
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        userTokenRepository.delete(userToken);
    }
    
    private void setExpires(UserToken userToken)
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE,AuthenticationService.SESSION_TIME);
        userToken.setExpires(cal.getTime());
    }
    
    public static AuthenticationService getInstance()
    {
        if(null == instance){
            instance = new AuthenticationService();
        }
        return instance;
    }
    
    
}
