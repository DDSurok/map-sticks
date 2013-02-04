package ru.ddsurok.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(HibernateUtil.class);
    
    static {
        try {
            Configuration config = new Configuration();
            config.configure();
            ServiceRegistryBuilder srBuilder = new ServiceRegistryBuilder();
            srBuilder.applySettings(config.getProperties());
            ServiceRegistry serviceRegistry = srBuilder.buildServiceRegistry();
            sessionFactory = config.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            log.error("Initial SessionFactory creation failed.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
