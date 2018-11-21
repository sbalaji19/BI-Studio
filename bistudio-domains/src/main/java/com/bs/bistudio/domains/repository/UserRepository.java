/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bs.bistudio.domains.repository;

import com.bs.bistudio.domains.base.BaseRepository;
import com.bs.bistudio.domains.entity.LoginToken;
import com.bs.bistudio.domains.entity.User;
import java.util.Objects;
import java.util.Optional;

/**
 *
 * @author Balaji.S <<sssbalajis@gmail.com>>
 */
public class UserRepository extends BaseRepository<User, Long> {

   
    public Optional<User> findByUsername(String username) {
        Objects.requireNonNull(username);
        return this.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }

   

    public Optional<User> findByLoginToken(String token, LoginToken.TokenType tokenType) {
        return entityManager().createNativeQuery(User.QUERY_BY_LOGIN_TOKEN, User.class)
                .setParameter("tokenHash", token)
                .setParameter("tokenType", tokenType)
                .getResultStream()
                .findFirst();
    }

}
