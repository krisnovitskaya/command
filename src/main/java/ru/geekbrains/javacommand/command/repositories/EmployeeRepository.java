package ru.geekbrains.javacommand.command.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.javacommand.command.entities.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select e from Employee e where e.user.userName = ?1")
    Employee findEmployeeByUsername(String username);

    @Query("select e from Employee e where e.department.id = ?1")
    List<Employee> findAllByDepartmentId(Long id);

    @Query("select e from Employee e where e.user.id = ?1")
    Employee findByUserId(Long id);
}


