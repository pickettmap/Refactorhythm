package com.refactorhythm.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public enum SessionUtility {
    INSTANCE;

    private SessionFactory instance;

    public SessionFactory getSessionFactoryInstance(){
        if(instance == null){
            instance = new Configuration().configure().buildSessionFactory();
        }
        return instance;
    }

}
