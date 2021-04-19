package ru.geekbrains.javacommand.command.util;

import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.repositories.specifications.ErrandSpecifications;

import java.time.OffsetDateTime;
import java.util.Map;

@Getter
public class ErrandFilter {

    private Specification<Errand> spec;

    public ErrandFilter(Map<String, String> params) {
        spec = Specification.where(null);

        if (params.containsKey("employee") && !params.get("employee").isBlank()) {
            Long employeeId = Long.parseLong(params.get("employee"));
            spec = spec.and(ErrandSpecifications.employeeIdIs(employeeId));
        }

        if (params.containsKey("department") && !params.get("department").isBlank()) {
            Long departmentId = Long.parseLong(params.get("department"));
            spec = spec.and(ErrandSpecifications.departmentIdIs(departmentId));
        }

        if (params.containsKey("errandMatterType") && !params.get("errandMatterType").isBlank()) {
            Long errandMatterTypeId = Long.parseLong(params.get("errandMatterType"));
            spec = spec.and(ErrandSpecifications.errandMatterTypeIdIs(errandMatterTypeId));
        }

        if (params.containsKey("dateStart") && !params.get("dateStart").isBlank()) {
            OffsetDateTime dateStart = OffsetDateTime.parse(params.get("dateStart"));
            spec = spec.and(ErrandSpecifications.dateStartGreaterOrEqualsThan(dateStart));
        }

        if (params.containsKey("dateStart2") && !params.get("dateStart2").isBlank()) {
            OffsetDateTime dateStart2 = OffsetDateTime.parse(params.get("dateStart2"));
            spec = spec.and(ErrandSpecifications.dateStartLessOrEqualsThan(dateStart2));
        }

        if (params.containsKey("dateEnd") && !params.get("dateEnd").isBlank()) {
            OffsetDateTime dateEnd = OffsetDateTime.parse(params.get("dateEnd"));
            spec = spec.and(ErrandSpecifications.dateEndLessOrEqualsThan(dateEnd));
        }

        if (params.containsKey("errandStatusType") && !params.get("errandStatusType").isBlank()) {
            Long statusId = Long.parseLong(params.get("errandStatusType"));
            spec = spec.and(ErrandSpecifications.statusIdIs(statusId));
        }

        if (params.containsKey("place") && !params.get("place").isBlank()) {
            Long placeId = Long.parseLong(params.get("place"));
            spec = spec.and(ErrandSpecifications.placeIdIs(placeId));
        }

        if (params.containsKey("placeType") && !params.get("placeType").isBlank()) {
            Long placeTypeId = Long.parseLong(params.get("placeType"));
            spec = spec.and(ErrandSpecifications.placeTypeIdIs(placeTypeId));
        }

    }
}
