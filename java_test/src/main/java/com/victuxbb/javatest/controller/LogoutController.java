/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.victuxbb.javatest.controller;

import com.victuxbb.javatest.model.user.User;
import com.victuxbb.javatest.security.RequestAuthorizationFilter;
import com.victuxbb.javatest.security.service.AuthenticationService;
import com.victuxbb.javatest.security.service.UserTokenService;
import com.victuxbb.javatest.security.token.UserToken;
import com.victuxbb.javatest.security.token.UserTokenNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rx.Observable;
import rx.functions.Action1;

/**
 *
 * @author victux
 */
public class LogoutController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        final String contextPath = request.getContextPath();
        final AuthenticationService authenticationService = AuthenticationService.getInstance();
        UserTokenService userTokenService = UserTokenService.getInstance();
        Observable<UserToken> userTokenObservable;
        try {
            userTokenObservable = userTokenService.getUserToken(req);      

            userTokenObservable.subscribe(
                new Action1<UserToken>() {
                    @Override
                    public void call(UserToken userToken) {

                        authenticationService.removeSession(userToken, res);                        
                        try {
                            res.sendRedirect(contextPath+"/login.jsp");
                        } catch (IOException ex) {
                            Logger.getLogger(LogoutController.class.getName()).log(Level.SEVERE, null, ex);
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
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
