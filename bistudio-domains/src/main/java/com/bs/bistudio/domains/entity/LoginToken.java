/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bs.bistudio.domains.entity;

import com.bs.bistudio.domains.base.BaseEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import static javax.persistence.EnumType.STRING;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

/**
 *
 * @author Balaji.S <<sssbalajis@gmail.com>>
 */
public class LoginToken extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public enum TokenType {
        REMEMBER_ME,
        API,
        RESET_PASSWORD
    }

    @Column(unique = true)
    private String tokenHash;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime expiredDate;

    @Column(length = 45)
    private String ipAddress;

    @Column
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(STRING)
    private TokenType type;

    @PrePersist
    public void beforeSave() {
        createdDate = LocalDateTime.now();

        if (expiredDate == null) {
            expiredDate = LocalDateTime.now().plusMonths(1L);
        }
    }
}
