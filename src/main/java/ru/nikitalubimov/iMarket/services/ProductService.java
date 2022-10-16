package ru.nikitalubimov.iMarket.services;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nikitalubimov.iMarket.dao.ProductDaoImpl;
import ru.nikitalubimov.iMarket.dao.UserDaoImpl;
import ru.nikitalubimov.iMarket.entity.Product;
import ru.nikitalubimov.iMarket.entity.User;
import ru.nikitalubimov.iMarket.repo.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private ProductDaoImpl productDao;

    @Autowired
    private UserDaoImpl userDao;


    public List<User> shoppingListProductById(long id) {
        return productDao.shoppingListProductById(id);
    }

    public List<Product> shoppingListUserById(long id) {
        return userDao.shoppingListUserById(id);
    }
}
