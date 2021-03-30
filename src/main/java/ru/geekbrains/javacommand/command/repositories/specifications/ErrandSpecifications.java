package ru.geekbrains.javacommand.command.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.javacommand.command.entities.Errand;

public class ErrandSpecifications {

    public static Specification<Errand> employeeIdIs(Long employeeId) {
        return (Specification<Errand>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("employee").get("id"), employeeId);
    }

    public static Specification<Errand> errandMatterTypeIdIs(Long errandMatterTypeId) {
        return (Specification<Errand>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("errandDetails").get("matter").get("id"), errandMatterTypeId);
    }
}
