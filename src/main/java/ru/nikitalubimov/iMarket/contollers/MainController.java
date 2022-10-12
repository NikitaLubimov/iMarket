package ru.nikitalubimov.iMarket.contollers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nikitalubimov.iMarket.dao.ProductDaoImpl;
import ru.nikitalubimov.iMarket.entity.Product;
import ru.nikitalubimov.iMarket.services.ProductService;

import java.util.List;


@RestController
public class MainController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDaoImpl productDao;

    @GetMapping("/products/all")
    public List<Product> getAllProductList () {
        return productDao.getAllProductList();
    }

    @GetMapping("/products/delete/{id}")
    public void deleteProduct(@PathVariable long id) {
        productDao.deleteProductById(id);
    }

    @RequestMapping(value = "/products/add", method = RequestMethod.POST)
    public void addProduct(@RequestBody Product product) {
        productDao.addProduct(product);
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "addProducts.html";
    }

    @GetMapping("/test")
    public String test(){

    }
}
