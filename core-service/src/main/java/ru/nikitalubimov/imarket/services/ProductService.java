package ru.nikitalubimov.iMarket.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.nikitalubimov.iMarket.api.ProductDto;
import ru.nikitalubimov.iMarket.converters.ProductMapper;
import ru.nikitalubimov.iMarket.data.Product;
import ru.nikitalubimov.iMarket.repositories.ProductRepository;
import ru.nikitalubimov.iMarket.repositories.specifications.ProductSpecification;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper MAPPER;


    public Page<ProductDto> findAll(Integer minCost, Integer maxCost, Integer page, String titlePart) {
        log.info("ProductService - > findAll");
        Specification<Product> specification = Specification.where(null);

        if (minCost != null) {
            specification = specification.and(ProductSpecification.costGreaterOrEqualsThan(minCost));
        }
        if (maxCost != null) {
            specification = specification.and(ProductSpecification.costLessThanOrEqualTo(maxCost));
        }
        if (titlePart != null) {
            specification = specification.and(ProductSpecification.titleLike(titlePart));
        }
        return productRepository.findAll(specification, PageRequest.of(page - 1, 5)).map(MAPPER::toDto);

    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public ProductDto addProduct(Product product) {
        log.info("ProductService - > addProduct");
        productRepository.save(product);
        return MAPPER.toDto(product);
    }

    public void deleteProductById(long id) {
        log.info("ProductService - > deleteProductById");
        productRepository.deleteById(id);
    }

}

