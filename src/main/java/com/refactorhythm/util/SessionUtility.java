package com.refactorhythm.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * This is a singleton sessionFactory. The session is configured by the hibernate.cfg.xml for the prod connection.
 * @author Brandon Pinkerton
 * @version 1.0
 */
public enum SessionUtility {
    INSTANCE;

    private SessionFactory instance;

    /**
     * This gets a session factory instance or creates it.
     * @return Returns the instance.
     */
    public SessionFactory getSessionFactoryInstance(){
        if(instance == null){
            instance = new Configuration().configure().buildSessionFactory();
        }
        return instance;
    }

}
