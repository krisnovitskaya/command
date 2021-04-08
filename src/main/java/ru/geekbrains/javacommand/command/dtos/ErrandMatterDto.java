package ru.geekbrains.javacommand.command.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.javacommand.command.entities.ErrandMatterType;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrandMatterDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("matter")
    private String matter;

    public ErrandMatterDto(Optional<ErrandMatterType> errandMatterType) {
        this.id = errandMatterType.get().getId();
        this.matter = errandMatterType.get().getMatter();
    }
    public ErrandMatterDto(ErrandMatterType errandMatterType) {
        this.id = errandMatterType.getId();
        this.matter = errandMatterType.getMatter();
    }
}
