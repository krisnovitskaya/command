package ru.geekbrains.javacommand.command.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.javacommand.command.entities.User;
import org.hibernate.validator.constraints.Length;
import ru.geekbrains.javacommand.command.entities.User;
import javax.persistence.Column;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("username")
    private String userName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("roles")
    private Set<String> roles;

    @JsonProperty("employee_id")
    private Long employeeId;

    public UserDto(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
    }
    public UserDto(Optional<User> user){
        this.id = user.get().getId();
        this.userName = user.get().getUserName();
        this.password = user.get().getPassword();
        this.roles = user.get().getListRoles().stream().map((r) -> r.getName()).collect(Collectors.toSet());
        this.employeeId = user.get().getEmployee().getId();
    }

}
