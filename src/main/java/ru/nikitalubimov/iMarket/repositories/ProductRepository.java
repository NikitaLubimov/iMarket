package ru.nikitalubimov.iMarket.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.nikitalubimov.iMarket.data.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> , JpaSpecificationExecutor<Product> {

    List<Product> findAllByCostBetween (Integer min, Integer max);

    List<Product> findAllByCostBefore(Integer max);

}
