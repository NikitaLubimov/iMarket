package ru.nikitalubimov.iMarket;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Component
public class SessionFactoryUtils {

    private SessionFactory factory;


    @PostConstruct
    public void init() {
        System.out.println("------------Start init method--------------------");
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        System.out.println("-----------------Create new Factory---------------");
    }

    public Session getSession () {
        return factory.getCurrentSession();
    }

    @PreDestroy
    public void close() {
        if (factory != null) {
            factory.close();
            System.out.println("-----------------Factory is close-------------------");
        }
    }
}
