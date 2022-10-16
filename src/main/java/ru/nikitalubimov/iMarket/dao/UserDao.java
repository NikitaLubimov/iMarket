package ru.nikitalubimov.iMarket.dao;

import ru.nikitalubimov.iMarket.entity.Product;
import ru.nikitalubimov.iMarket.entity.User;

import java.util.List;

public interface UserDao {

    public User findUserById(long id);
    public List<Product> shoppingListUserById(long id);
}
