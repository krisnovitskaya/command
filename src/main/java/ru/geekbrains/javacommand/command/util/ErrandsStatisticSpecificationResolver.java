package ru.geekbrains.javacommand.command.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.javacommand.command.entities.Errand;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Map;

/**
 * @author owpk
 */
@Slf4j
public class ErrandsStatisticSpecificationResolver {

    private final Map<String, String> specs;
    private Specification<Errand> defaultSpec;

    public ErrandsStatisticSpecificationResolver(Map<String, String> specs) {
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
            if (specs.get(key) != null && !specs.get(key).isBlank())
                defaultSpec = defaultSpec.and(specification);
            else log.warn("empty specification with key: {}", key);
        }
    }

    // TODO b_strap dateTIMEpicker
    // TODO delete default time
    private static OffsetDateTime parseDate(String date) {
        DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
                .appendPattern("dd-MM-yyyy HH:mm:ss")
                .parseDefaulting(ChronoField.OFFSET_SECONDS, 0)
                .toFormatter();
        return OffsetDateTime.parse(date + " 00:00:00", FORMATTER);
    }
}