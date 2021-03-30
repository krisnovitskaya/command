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

        if (params.containsKey("employee") && !params.get("employee").trim().isEmpty()) {
            Long employeeId = Long.parseLong(params.get("employee"));
            spec = spec.and(ErrandSpecifications.employeeIdIs(employeeId));
        }

        if (params.containsKey("errandMatterType") && !params.get("errandMatterType").trim().isEmpty()) {
            Long errandMatterTypeId = Long.parseLong(params.get("errandMatterType"));
            spec = spec.and(ErrandSpecifications.errandMatterTypeIdIs(errandMatterTypeId));
        }

    }
}
