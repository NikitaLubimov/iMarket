package ru.nikitalubimov.iMarket.repo;


import org.springframework.stereotype.Component;
import ru.nikitalubimov.iMarket.dto.Product;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Component
public class ProductRepository {

    private List<Product> repoProducts;

    @PostConstruct
    public void init() {
        repoProducts = new ArrayList<>(Arrays.asList(
                new Product(1l,"Milk",100),
                new Product(2l,"Bread",150),
                new Product(3l,"Kit-Kat", 200)
        ));
    }


    public Product getProductById(Long id) {
        return repoProducts.stream().filter(product -> product.getId() == id).findFirst().orElseThrow();
    }

    public List<Product> getAllProduct() {
       return Collections.unmodifiableList(repoProducts);
    }

    public void addProduct (Product product) {
        repoProducts.add(product);
    }

    public void deleteProductById(long id) {
        repoProducts.removeIf(product -> product.getId() == id);
    }
}
