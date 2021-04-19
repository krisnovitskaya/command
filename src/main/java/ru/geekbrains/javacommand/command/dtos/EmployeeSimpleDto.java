package ru.geekbrains.javacommand.command.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.javacommand.command.entities.Employee;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSimpleDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("fio")
    private String fio;

    public EmployeeSimpleDto(Employee employee){
        this.id = employee.getId();
        this.fio = String.format("%s %s %s", employee.getLastName(), employee.getFirstName(), employee.getMiddleName());
    }

    public EmployeeSimpleDto(EmployeeDto employee){
        this.id = employee.getId();
        this.fio = String.format("%s %s %s", employee.getLastName(), employee.getFirstName(), employee.getMiddleName());
    }
}
