package ru.nikitalubimov.iMarket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.nikitalubimov.iMarket.data.Product;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("ru.nikitalubimov.iMarket")
public class MyConf {

    @Bean
    @Scope("prototype")
    public List<Product> productList() {
        return new ArrayList<>();
    }
}
