/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.victuxbb.javatest.controller;

import com.victuxbb.javatest.model.user.User;
import com.victuxbb.javatest.model.user.UserNotFoundException;
import com.victuxbb.javatest.model.user.UserRepositoryFactory;
import com.victuxbb.javatest.model.user.UserRepositoryInterface;
import com.victuxbb.javatest.security.service.AuthenticationService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author victux
 */
public class LoginController extends HttpServlet {

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
        
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String contextPath = request.getContextPath();
        
        UserRepositoryInterface userRepository = UserRepositoryFactory.getInstance().getUserRepository();
        try{
            
            User user = userRepository.findUserByUsernameAndPassword(username, password);
            AuthenticationService authentication = AuthenticationService.getInstance();
            authentication.createSession(user, response);
            request.setAttribute("username", user.getUsername());
            /*
             * Enviamos by default al usuario a la página del primer rol que tenga...entendemos que 1 role es 1 página :)
            */
            String page = user.getRoles().get(0).name().toLowerCase();
            
            response.sendRedirect(contextPath+"/private/"+page+".jsp");
            
        }catch(UserNotFoundException e){
            
            response.sendRedirect(contextPath+"/login.jsp?error="+e.getMessage());
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
