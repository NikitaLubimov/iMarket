package ru.nikitalubimov.iMarket.repositories.specifications;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.nikitalubimov.iMarket.data.Product;

@Component
public class ProductSpecification {
    public static Specification<Product> costGreaterOrEqualsThan(Integer minCost) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), minCost);
    }

    public static Specification<Product> costLessThanOrEqualTo (Integer maxCost) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), maxCost);
    }

    public static Specification<Product> titleLike (String titlePart) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));
    }
}
