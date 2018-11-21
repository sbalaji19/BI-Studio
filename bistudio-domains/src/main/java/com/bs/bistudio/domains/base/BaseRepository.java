/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bs.bistudio.domains.base;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.SynchronizationType;

/**
 *
 * @author Balaji.S <<sssbalajis@gmail.com>> 
 */
public class BaseRepository<T extends BaseEntity, ID> extends AbstractBaseRepository<T, ID> {

    private ConcurrentHashMap<String, EntityManagerFactory> factoryMap = new ConcurrentHashMap<>();

   
    @Produces    
    @Override
    protected EntityManager entityManager() {
        return fetchEntityManagerFactory().createEntityManager(SynchronizationType.SYNCHRONIZED);
    }

    private EntityManagerFactory fetchEntityManagerFactory() {

        String url = "";

        this.factoryMap.computeIfAbsent(url, u -> {
            Map<String, String> prop = new HashMap<>();

            prop.put("javax.persistence.jdbc.url", url);

            prop.put("eclipselink.ddl-generation", "create-or-extend-tables");
            prop.put("eclipselink.ddl-generation.output-mode", "database");
            prop.put("hibernate.hbm2ddl.auto", "update");

            return Persistence.createEntityManagerFactory("bistudio", prop);
        });
        return this.factoryMap.get(url);
    }

    void closeEntityManager(@Disposes EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }

    @PreDestroy
    void preDestroy() {
        this.factoryMap.forEach((url, emf) -> emf.close());
    }
}
