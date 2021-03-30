package ru.geekbrains.javacommand.command.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.geekbrains.javacommand.command.entities.User;

import javax.persistence.Column;
import java.util.Optional;

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

    public UserDto(Optional<User> user){
        this.userName = user.get().getUserName();
        this.password = user.get().getPassword();
    }
}
