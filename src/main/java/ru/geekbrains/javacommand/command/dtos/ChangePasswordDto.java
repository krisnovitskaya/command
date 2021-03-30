package ru.geekbrains.javacommand.command.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDto {

    @NotNull
    private String currentPass;

    @Length(min = 8, max = 20)
    private String newPass;

    @Length(min = 8, max = 20)
    private String retypeNewPass;
}
