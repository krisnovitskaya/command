package ru.geekbrains.javacommand.command.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.javacommand.command.entities.Errand;

public class ErrandSpecifications {
    public static Specification<Errand> priceGreaterOrEqualsThan(int minPrice) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);  // where p.price >= minPrice
    }

    public static Specification<Errand> priceLesserOrEqualsThan(int maxPrice) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice); // where p.price <= maxPrice
    }

    public static Specification<Errand> titleLike(String titlePart) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart)); // where p.title like %titlePart%
    }

    public static Specification<Errand> categoryIdIs(Long categoryId) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("category").get("id"), categoryId);
    }
}
