package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.dtos.ProfileDto;

public interface EmployeeService {

    ProfileDto getProfile(String name);
}
