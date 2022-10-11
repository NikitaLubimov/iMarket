package ru.nikitalubimov.iMarket.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nikitalubimov.iMarket.SessionFactoryUtils;
import ru.nikitalubimov.iMarket.entity.Product;

import java.util.List;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    SessionFactoryUtils sessionFactoryUtils;

    @Override
    public void addProduct(Product product) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteProductById(Long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            Product product = new Product();
            product.setId(id);
            session.delete(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public Product findProductById(long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public List<Product> getAllProductList() {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            List<Product> productList = session.createQuery("select p from Product p").getResultList();
            session.getTransaction().commit();
            return productList;
        }
    }
}
