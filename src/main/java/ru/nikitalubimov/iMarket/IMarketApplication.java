package ru.nikitalubimov.iMarket;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.nikitalubimov.iMarket.dao.ProductDaoImpl;

@SpringBootApplication
public class IMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(IMarketApplication.class, args);
    }
}
