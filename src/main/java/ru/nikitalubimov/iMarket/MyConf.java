package ru.nikitalubimov.iMarket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.nikitalubimov.iMarket.data.Product;
import ru.nikitalubimov.iMarket.dto.CartItem;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("ru.nikitalubimov.iMarket")
public class MyConf {

    @Bean
    @Scope("prototype")
    public List<Product> arrayList() {
        return new ArrayList<>();
    }
}
