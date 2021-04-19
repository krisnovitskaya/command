package ru.geekbrains.javacommand.command.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.javacommand.command.entities.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    public RoleDto(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }
}
