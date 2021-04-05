package ru.geekbrains.javacommand.command.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.javacommand.command.entities.Department;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    public DepartmentDto(Department department) {
        this.id = department.getId();
        this.title = department.getTitle();
    }

}
