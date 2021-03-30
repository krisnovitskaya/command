package ru.geekbrains.javacommand.command.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.javacommand.command.entities.Department;
import ru.geekbrains.javacommand.command.entities.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByMaster(Employee master);
}
