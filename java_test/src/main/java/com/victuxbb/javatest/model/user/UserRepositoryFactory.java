/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.victuxbb.javatest.model.user;

/**
 *
 * @author victux
 */
public class UserRepositoryFactory
{
    private UserRepositoryInterface instanceRepository;
    private static UserRepositoryFactory instanceFactory;
            
    
    public static UserRepositoryFactory getInstance()
    {
        if(null == instanceFactory) {
            instanceFactory = new UserRepositoryFactory();
        }
        return instanceFactory;
                
    }
    
    public UserRepositoryInterface getUserRepository()
    {
        if(null == instanceRepository){
            instanceRepository = new InMemoryUserRepository();
        }
        return instanceRepository;
    }
    
    
}
