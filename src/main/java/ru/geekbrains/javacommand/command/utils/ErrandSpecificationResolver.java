package ru.geekbrains.javacommand.command.utils;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.javacommand.command.entities.Errand;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @author owpk
 */
public class ErrandSpecificationResolver {

    private final Map<String, String> specs;
    private Specification<Errand> defaultSpec;

    public ErrandSpecificationResolver(Map<String, String> specs) {
        this.specs = specs;
        defaultSpec = Specification.where(null);
    }

    public Specification<Errand> resolve() {
        appendIfContains("dep", (r, cq, cb) ->
                cb.like(r.get("employee").get("department").get("title"), specs.get("dep")));

        appendIfContains("empl", (r, cq, cb) ->
                cb.like(r.get("employee").get("firstName"), specs.get("empl")));

        appendIfContains( "pl_type",(r, cq, cb) ->
                cb.like(r.get("errandDetails").get("place").get("title"), specs.get("pl_type")));

        appendIfContains("place", (r, cq, cb) ->
                cb.like(r.get("errandDetails").get("place").get("title"), specs.get("place")));

        appendIfContains("er_type", (r, cq, cb) ->
                cb.like(r.get("errandDetails").get("matter").get("matter"), specs.get("er_type")));

        appendIfContains("err_status", (r, cq, cb) ->
                cb.like(r.get("statusType").get("status"), specs.get("er_type")));

        appendIfContains("date_from", (r, cq, cb) ->
                cb.greaterThan(r.get("dateStart"), parseDate(specs.get("date_from"))));

        appendIfContains("date_to", (r, cq, cb) ->
                cb.lessThan(r.get("dateEnd"), parseDate(specs.get("date_to"))));
        return defaultSpec;
    }

    private void appendIfContains(String key, Specification<Errand> specification) {
        if (specs.containsKey(key)) {
           defaultSpec = defaultSpec.and(specification);
        }
    }

    private static OffsetDateTime parseDate(String date) {
        return OffsetDateTime.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}