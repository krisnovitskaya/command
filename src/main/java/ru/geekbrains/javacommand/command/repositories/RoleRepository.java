package ru.geekbrains.javacommand.command.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.javacommand.command.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
