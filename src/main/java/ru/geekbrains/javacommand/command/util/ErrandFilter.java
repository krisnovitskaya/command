package ru.geekbrains.javacommand.command.util;

import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.repositories.specifications.ErrandSpecifications;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class ErrandFilter {

    private Specification<Errand> spec;

    public ErrandFilter(MultiValueMap<String, String> params) {
        spec = Specification.where(null);

        if (params.getFirst("employee") != null && !params.getFirst("employee").isBlank()) {
            Long employeeId = Long.parseLong(params.getFirst("employee"));
            spec = spec.and(ErrandSpecifications.employeeIdIs(employeeId));
        }

        if (params.containsKey("department")) {
            List<String> departments = params.get("department");
            List<Long> departmentsIDs = departments.stream().map(d -> Long.parseLong(d)).collect(Collectors.toList());
            spec = spec.and(ErrandSpecifications.departmentIdIs(departmentsIDs));
        }

        if (params.getFirst("errandMatterType") != null && !params.getFirst("errandMatterType").isBlank()) {
            Long errandMatterTypeId = Long.parseLong(params.getFirst("errandMatterType"));
            spec = spec.and(ErrandSpecifications.errandMatterTypeIdIs(errandMatterTypeId));
        }

        if (params.getFirst("dateStart") != null && !params.getFirst("dateStart").isBlank()) {
            OffsetDateTime dateStart = OffsetDateTime.parse(params.getFirst("dateStart"));
            spec = spec.and(ErrandSpecifications.dateStartGreaterOrEqualsThan(dateStart));
        }

        if (params.getFirst("dateStart2") != null && !params.getFirst("dateStart2").isBlank()) {
            OffsetDateTime dateStart2 = OffsetDateTime.parse(params.getFirst("dateStart2"));
            spec = spec.and(ErrandSpecifications.dateStartLessOrEqualsThan(dateStart2));
        }

        if (params.getFirst("dateEnd") != null && !params.getFirst("dateEnd").isBlank()) {
            OffsetDateTime dateEnd = OffsetDateTime.parse(params.getFirst("dateEnd"));
            spec = spec.and(ErrandSpecifications.dateEndLessOrEqualsThan(dateEnd));
        }

        if (params.getFirst("errandStatusType") != null && !params.getFirst("errandStatusType").isBlank()) {
            Long statusId = Long.parseLong(params.getFirst("errandStatusType"));
            spec = spec.and(ErrandSpecifications.statusIdIs(statusId));
        }

        if (params.getFirst("place") != null && !params.getFirst("place").isBlank()) {
            Long placeId = Long.parseLong(params.getFirst("place"));
            spec = spec.and(ErrandSpecifications.placeIdIs(placeId));
        }

        if (params.getFirst("placeType") != null && !params.getFirst("placeType").isBlank()) {
            Long placeTypeId = Long.parseLong(params.getFirst("placeType"));
            spec = spec.and(ErrandSpecifications.placeTypeIdIs(placeTypeId));
        }

    }
}
