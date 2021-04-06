package ru.geekbrains.javacommand.command.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.javacommand.command.entities.Department;

@Data
@NoArgsConstructor
public class DepartmentDto {
    private Long id;
    private String title;

    @JsonProperty("master_department")
    private String masterDepartment;

    public DepartmentDto(Department dep) {
        this.id = dep.getId();
        this.title = dep.getTitle();
        if (dep.getMasterDepartment() != null)
            this.masterDepartment = dep.getMasterDepartment().getTitle();
    }
}
