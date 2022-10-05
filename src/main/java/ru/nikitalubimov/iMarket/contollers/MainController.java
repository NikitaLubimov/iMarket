package ru.nikitalubimov.iMarket.contollers;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nikitalubimov.iMarket.dto.Product;
import ru.nikitalubimov.iMarket.services.ProductService;

import java.util.List;


@RestController
public class MainController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/all")
    public List<Product> getAllProductList () {
        return productService.getAllProductList();
    }

    @GetMapping("/products/delete/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/products/add/{id},{title},{cost}")
    public void addProduct(@PathVariable long id, @PathVariable String title, @PathVariable int cost) {
        Product product = new Product(id,title,cost);
        productService.addProductRepo(product);
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "addProducts.html";
    }

    @PostMapping
    public String create( @ModelAttribute("product") Product product) {
        productService.addProductRepo(product);
        return "redirect:/products";
    }

}
