/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bs.bistudio.util;

import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @author Balaji Srinivasan
 */
@Dependent
public class LoggerProducer {

    /**
     * Create a suitable {@link Logger} instance depending on the actual bean
     * the instance is injected into.
     *
     * @param injectionPoint the injection point
     * @return the instance
     */
    @Produces
    public Logger getLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getBean().getBeanClass().getName());
    }
}
