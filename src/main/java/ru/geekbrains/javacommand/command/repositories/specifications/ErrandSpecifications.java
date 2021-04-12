package ru.geekbrains.javacommand.command.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.javacommand.command.entities.Errand;

import java.time.OffsetDateTime;

public class ErrandSpecifications {

    public static Specification<Errand> employeeIdIs(Long employeeId) {
        return (Specification<Errand>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("employee").get("id"), employeeId);
    }

    public static Specification<Errand> statusIs(String status) {
        return (Specification<Errand>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("statusType").get("status"), status);
    }

    public static Specification<Errand> statusIdIs(Long statusId) {
        return (Specification<Errand>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("statusType").get("id"), statusId);
    }

    public static Specification<Errand> errandMatterTypeIdIs(Long errandMatterTypeId) {
        return (Specification<Errand>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("errandDetails").get("matter").get("id"), errandMatterTypeId);
    }

    public static Specification<Errand> dateStartGreaterOrEqualsThan(OffsetDateTime dateStart1) {
        return (Specification<Errand>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("dateStart"), dateStart1);
    }

    public static Specification<Errand> dateStartLessOrEqualsThan(OffsetDateTime dateStart2) {
        return (Specification<Errand>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("dateStart"), dateStart2);
    }

    public static Specification<Errand> departmentIdIs(Long departmentId) {
        return (Specification<Errand>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("employee").get("department").get("id"), departmentId);
    }
}
