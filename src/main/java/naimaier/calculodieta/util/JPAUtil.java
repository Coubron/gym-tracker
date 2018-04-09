/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naimaier.calculodieta.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author coubr
 */
public class JPAUtil {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("DietaPU");
    
    public EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
