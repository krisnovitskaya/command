package ru.geekbrains.javacommand.command.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.javacommand.command.entities.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.javacommand.command.entities.Department;
import ru.geekbrains.javacommand.command.entities.Employee;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("title")
    private String title;

    @JsonProperty("master_department")
    private String masterDepartment;

		@JsonProperty("department")
    private List<Employee> employees;

    public DepartmentDto(Department dep) {
        this.id = dep.getId();
        this.title = dep.getTitle();
        if (dep.getMasterDepartment() != null)
            this.masterDepartment = dep.getMasterDepartment().getTitle();
    }
}
