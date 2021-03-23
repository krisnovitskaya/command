package ru.geekbrains.javacommand.command.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.javacommand.command.entities.ErrandMatterType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrandMatterDto {
    private Long id;
    private String matter;

    public ErrandMatterDto(ErrandMatterType errandMatterType) {
        this.id = errandMatterType.getId();
        this.matter = errandMatterType.getMatter();
    }
}
