package ru.nikitalubimov.iMarket.contollers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nikitalubimov.iMarket.dto.Product;
import ru.nikitalubimov.iMarket.services.ProductService;


@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class MainController {

    private final ProductService productService;


    @GetMapping()
    public String getProduct(Model model) {
        model.addAttribute("listProducts",productService.getAllProductList());
        return "RepoProducts.html";
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
