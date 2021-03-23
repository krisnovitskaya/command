package ru.lightcrm.entities.datetest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName("payments")
@Validated
public class PaymentModel implements Serializable {

    @JsonProperty("time")
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonDeserialize(using = CustomDeserializer.class)
    private OffsetDateTime time;

}

class CustomDeserializer extends JsonDeserializer<OffsetDateTime> {

    private final DateTimeFormatter formatter;

    public CustomDeserializer() {
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    }

    public CustomDeserializer(
            DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public OffsetDateTime deserialize(JsonParser parser, DeserializationContext context) throws DateTimeParseException, IOException {
        String date = parser.getText();
        if (date.contains("+03:00")) {
            return OffsetDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
        }
        LocalDateTime ldt = LocalDateTime.parse(date, formatter);
        ZonedDateTime ldtZoned = ldt.atZone(ZoneId.systemDefault());
        ZonedDateTime utcZoned = ldtZoned.withZoneSameInstant(ZoneId.of("UTC"));
        return utcZoned.toOffsetDateTime();
    }
}
