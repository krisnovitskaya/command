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

        if (params.containsKey("errandMatterType") && !params.get("errandMatterType").isBlank()) {
            Long errandMatterTypeId = Long.parseLong(params.get("errandMatterType"));
            spec = spec.and(ErrandSpecifications.errandMatterTypeIdIs(errandMatterTypeId));
        }

        if (params.containsKey("dateStart1") && !params.get("dateStart1").isBlank()) {
            OffsetDateTime dateStart1 = OffsetDateTime.parse(params.get("dateStart1"));
            spec = spec.and(ErrandSpecifications.dateStartGreaterOrEqualsThan(dateStart1));
        }

        if (params.containsKey("dateStart2") && !params.get("dateStart2").isBlank()) {
            OffsetDateTime dateStart2 = OffsetDateTime.parse(params.get("dateStart2"));
            spec = spec.and(ErrandSpecifications.dateStartLessOrEqualsThan(dateStart2));
        }

    }
}
