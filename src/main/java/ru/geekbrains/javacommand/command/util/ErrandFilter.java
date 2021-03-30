package ru.geekbrains.javacommand.command.util;

import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.repositories.specifications.ErrandSpecifications;

import java.util.Map;

@Getter
public class ErrandFilter {

    private Specification<Errand> spec;

    public ErrandFilter(Map<String, String> params) {
        spec = Specification.where(null);

        String filterTitle = params.get("title");
        if (filterTitle != null && !filterTitle.trim().isEmpty()) {
            spec = spec.and(ErrandSpecifications.titleLike(filterTitle));
        }

        if (params.containsKey("min_price") && !params.get("min_price").trim().isEmpty()) {
            Integer minPrice = Integer.parseInt(params.get("min_price"));
            spec = spec.and(ErrandSpecifications.priceGreaterOrEqualsThan(minPrice));
        }

        if (params.containsKey("max_price") && !params.get("max_price").trim().isEmpty()) {
            Integer maxPrice = Integer.parseInt(params.get("max_price"));
            spec = spec.and(ErrandSpecifications.priceLesserOrEqualsThan(maxPrice));
        }
        if (params.containsKey("category") && !params.get("category").trim().isEmpty()) {
            Long categoryId = Long.parseLong(params.get("category"));
            spec = spec.and(ErrandSpecifications.categoryIdIs(categoryId));
        }

    }
}
