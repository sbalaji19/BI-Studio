/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bs.bistudio.domains.util;

import com.bs.bistudio.domains.base.BaseAuditableEntity;
import java.time.LocalDateTime;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author sssbalajis@gmail.com
 */
public class AuditEntityListener {

    @PrePersist
    public void beforePersist(Object entity) {
        if (entity instanceof BaseAuditableEntity) {
            BaseAuditableEntity o = (BaseAuditableEntity) entity;
            final LocalDateTime now = LocalDateTime.now();
            o.setCreatedDate(now);
            o.setLastModifiedDate(now);

            if (o.getCreatedBy() == null) {
                o.setCreatedBy(currentUser());
            }
        }
    }

    @PreUpdate
    public void beforeUpdate(Object entity) {
        if (entity instanceof BaseAuditableEntity) {
            BaseAuditableEntity o = (BaseAuditableEntity) entity;
            o.setLastModifiedDate(LocalDateTime.now());

            if (o.getLastModifiedBy() == null) {
                o.setLastModifiedBy(currentUser());
            }
        }
    }

    private String currentUser() {
        /*
        UserInfo user = CDI.current().select(UserInfo.class, Authenticated.INSTANCE).get();
        LOG.log(Level.FINEST, "get current user form EntityListener@{0}", user);
        if (user == null) {
            return null;
        }
        return user.getName();
         */
        return null;
    }
}
