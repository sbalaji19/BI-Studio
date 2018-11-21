/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bs.bistudio.domains.repository;

import com.bs.bistudio.domains.entity.User;
import java.util.Optional;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import static org.jboss.shrinkwrap.api.ShrinkWrap.create;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Balaji.S <<sssbalajis@gmail.com>>
 */
@RunWith(Arquillian.class)
public class UserRepositoryTest {

    @Deployment
    public static Archive<?> createTestArchive() {
        return create(JavaArchive.class ,"test.jar")
                .addClasses(UserRepository.class)
                .addAsManifestResource("META-INF/persistence.xml");
               
    }

    @Inject
    UserRepository userRepository;
    
    @Test
    public void testfindByUsername() {
       Optional<User> user =  userRepository.findByUsername("Reza");
        
        assertEquals(true, user.isPresent());
    }

}
