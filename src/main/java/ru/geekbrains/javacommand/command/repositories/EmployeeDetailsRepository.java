package ru.geekbrains.javacommand.command.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.entities.EmployeeDetails;

import java.util.List;

@Repository
public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails, Long> {

    @Query("select e from Employee e where e.user.id = ?1")
    Employee findByUserId(Long id);

    @Query("select e from Employee e where e.department.id = ?1")
    List<Employee> findAllByDepartmentId(Long id);

    @Query("select e from Employee e where e.user.userName = ?1")
    Employee findEmployeeByUsername(String username);

    Employee findEmployeeById(Long id);
}
