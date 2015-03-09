/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.victuxbb.javatest.security.token;

import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.Subscriber;

/**
 *
 * @author victux
 */
public class InMemoryUserTokenRepository implements UserTokenRepositoryInterface
{

    private List<UserToken> tokens;
    private static InMemoryUserTokenRepository instance;
    
    public InMemoryUserTokenRepository() {
        
        this.tokens = new ArrayList();        
        
    }
    
    @Override
    public Observable<UserToken> findByUuid(final String uuid){
        
        Observable<UserToken> userTokenObservable = Observable.create(new Observable.OnSubscribe<UserToken>() {            
                        
            @Override
            public void call(Subscriber subscriber) {
                boolean found = false;
                for (UserToken token : tokens) {
                    if(token.getUuid().equals(uuid)){
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(token);
                        }
                        found = true;
                        break;
                    }
                }                
                if(!found) subscriber.onError(new UserTokenNotFoundException("Token not found"));                             
                
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();                
                }
            }                        
        });
        
        return userTokenObservable;     
        
    }

    @Override
    public void save(UserToken userToken) {
        this.tokens.add(userToken);
    }
    
    
    @Override
    public void update(UserToken userToken) {
        /*
         * No hacemos nada ya que el objeto queda actualizado por referencia.
        */
    }
    
    public static InMemoryUserTokenRepository getInstance()
    {
        if(null == instance){
            instance = new InMemoryUserTokenRepository();
        }
        return instance;
    }

    @Override
    public void delete(UserToken userToken) {
        this.tokens.remove(userToken);
    }

    
    
    
    
    
}
