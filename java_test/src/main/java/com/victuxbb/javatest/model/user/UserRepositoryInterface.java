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
public interface UserRepositoryInterface
{
    public User findUserByUsernameAndPassword(String username,String password) throws UserNotFoundException;
}
