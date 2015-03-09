/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.victuxbb.javatest.security;


import com.victuxbb.javatest.model.user.User;
import com.victuxbb.javatest.security.service.AuthenticationService;
import com.victuxbb.javatest.security.service.AuthorizationService;
import com.victuxbb.javatest.security.service.UserTokenService;

import com.victuxbb.javatest.security.token.UserToken;
import com.victuxbb.javatest.security.token.UserTokenNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rx.Observable;
import rx.functions.Action1;


/**
 *
 * @author victux
 */
public class RequestAuthorizationFilter implements Filter
{
    private ServletContext context;
     
    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthorizationFilter initialized");
    }
     
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
 
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        final String contextPath = req.getContextPath();
        final AuthorizationService authorizationService = AuthorizationService.getInstance();
        final AuthenticationService authenticationService = AuthenticationService.getInstance();
        UserTokenService userTokenService = UserTokenService.getInstance(); 
        
        final String uri = req.getRequestURI();        
        
        Observable<UserToken> userTokenObservable;
        try {
            userTokenObservable = userTokenService.getUserToken(req);      

            userTokenObservable.subscribe(
                new Action1<UserToken>() {
                    @Override
                    public void call(UserToken userToken) {

                        authenticationService.refreshSession(userToken, res);
                        User user = userToken.getUser();
                        req.setAttribute("username",user.getUsername());
                        if(!authorizationService.isAuthorized(uri,userToken) ){
                            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            try {
                                res.sendRedirect(contextPath+"/forbidden.jsp");                                
                                return;
                            } catch (IOException ex) {
                                Logger.getLogger(RequestAuthorizationFilter.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        
                    }
                },
                new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        try {                    
                            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            res.sendRedirect(contextPath+"/login.jsp");
                            return;
                        } catch (IOException ex) {
                            Logger.getLogger(RequestAuthorizationFilter.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            );
        } catch (UserTokenNotFoundException ex) {
            res.sendRedirect(contextPath+"/login.jsp");
            return;
        }
                
        chain.doFilter(request, response);
         
         
    }
    
     
 
    public void destroy() {
        //close any resources here
    }
    
}
