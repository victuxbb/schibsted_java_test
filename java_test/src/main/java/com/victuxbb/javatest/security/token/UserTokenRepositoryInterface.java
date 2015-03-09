/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.victuxbb.javatest.security.token;

import rx.Observable;

/**
 *
 * @author victux
 */
interface UserTokenRepositoryInterface 
{
    public Observable<UserToken> findByUuid(String uuid);
    public void save(UserToken userToken);
    public void update(UserToken userToken);
    public void delete(UserToken userToken);
}
