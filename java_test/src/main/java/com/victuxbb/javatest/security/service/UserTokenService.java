/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.victuxbb.javatest.security.service;


import com.victuxbb.javatest.security.token.InMemoryUserTokenRepository;
import com.victuxbb.javatest.security.token.UserToken;
import com.victuxbb.javatest.security.token.UserTokenNotFoundException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import rx.Observable;


/**
 *
 * @author victux
 */

public class UserTokenService {
    
    private InMemoryUserTokenRepository inMemoryUserToken;
    private static UserTokenService instance;

    public UserTokenService() {
        this.inMemoryUserToken = InMemoryUserTokenRepository.getInstance();
        
    }   
    
    
    public Observable<UserToken> getUserToken(HttpServletRequest req) throws UserTokenNotFoundException
    {
        Cookie cookie = getCookie(AuthenticationService.SESSION_COOKIE_NAME, req);
        String tokenId = cookie.getValue();           
        return this.inMemoryUserToken.findByUuid(tokenId);                
    }    
    
    private Cookie getCookie(String cookieName , HttpServletRequest request) throws UserTokenNotFoundException{
        Cookie[] cookies = request.getCookies();
        for (Cookie cooky : cookies) {
            if( cooky.getName().equals(cookieName) ){
                return cooky;
            }
        }
        throw new UserTokenNotFoundException("SESSION NOT FOUND");
    }
    
    public static UserTokenService getInstance()
    {
        if(null == instance){
            instance = new UserTokenService();
        }
        return instance;
    }
    
}
