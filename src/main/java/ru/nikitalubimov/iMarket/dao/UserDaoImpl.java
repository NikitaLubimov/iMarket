package ru.nikitalubimov.iMarket.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nikitalubimov.iMarket.SessionFactoryUtils;
import ru.nikitalubimov.iMarket.entity.Product;
import ru.nikitalubimov.iMarket.entity.User;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    SessionFactoryUtils sessionFactoryUtils;

    @Override
    public User findUserById(long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.getTransaction().commit();
            return user;
        }
    }

    @Override
    public List<Product> shoppingListUserById(long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            List<Product> userProducts= user.getProducts();
            session.getTransaction().commit();
            return userProducts;
        }
    }
}
