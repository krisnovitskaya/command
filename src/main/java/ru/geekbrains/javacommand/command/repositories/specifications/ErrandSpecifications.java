package ru.geekbrains.javacommand.command.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.javacommand.command.entities.Department;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.entities.Errand;

import javax.persistence.criteria.*;
import java.time.OffsetDateTime;
import java.util.List;

public class ErrandSpecifications {

    public static Specification<Errand> employeeIdIs(Long employeeId) {
        return (Specification<Errand>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("employee").get("id"), employeeId);
    }

    public static Specification<Errand> statusIdIs(Long statusId) {
        return (Specification<Errand>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("statusType").get("id"), statusId);
    }

    public static Specification<Errand> errandMatterTypeIdIs(Long errandMatterTypeId) {
        return (Specification<Errand>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("errandDetails").get("matter").get("id"), errandMatterTypeId);
    }

    public static Specification<Errand> dateStartGreaterOrEqualsThan(OffsetDateTime dateStart) {
        return (Specification<Errand>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("dateStart"), dateStart);
    }

    public static Specification<Errand> dateStartLessOrEqualsThan(OffsetDateTime dateStart2) {
        return (Specification<Errand>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("dateStart"), dateStart2);
    }

    public static Specification<Errand> departmentIdIs(List<Long> departmentIds) {
        return new Specification<Errand>() {
            public Predicate toPredicate(Root<Errand> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.distinct(true);
                Join<Errand, Employee> errandEmployeeJoin = root.join("employee");
                Join<Employee, Department> employeeDepartmentJoin = errandEmployeeJoin.join("department");

                return employeeDepartmentJoin.get("id").in(departmentIds);
            }
        };
    }


    public static Specification<Errand> dateEndLessOrEqualsThan(OffsetDateTime dateEnd) {
        return (Specification<Errand>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("dateEnd"), dateEnd);
    }

    public static Specification<Errand> placeIdIs(Long placeId) {
        return (Specification<Errand>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("errandDetails").get("place").get("id"), placeId);
    }

    public static Specification<Errand> placeTypeIdIs(Long placeTypeId) {
        return (Specification<Errand>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("errandDetails").get("place").get("placeType").get("id"), placeTypeId);
    }
}
