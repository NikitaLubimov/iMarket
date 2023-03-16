package ru.nikitalubimov.iMarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nikitalubimov.iMarket.data.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}