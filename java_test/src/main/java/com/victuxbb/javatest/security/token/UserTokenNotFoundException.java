/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.victuxbb.javatest.security.token;

/**
 *
 * @author victux
 */
public class UserTokenNotFoundException extends Exception{

    public UserTokenNotFoundException(String string) {
        super(string);
    }
    
}
