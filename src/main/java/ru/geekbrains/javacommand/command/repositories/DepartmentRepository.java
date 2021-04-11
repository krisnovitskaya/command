package ru.geekbrains.javacommand.command.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.javacommand.command.dtos.DepartmentSimpleDto;
import ru.geekbrains.javacommand.command.entities.Department;
import ru.geekbrains.javacommand.command.entities.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findDepartmentByTitle(String title);
    Optional<Department> findByMaster(Employee master);


    @Query(value = "WITH RECURSIVE r AS (\n" +
            "   SELECT id, title, master_id, master_department_id, created, updated\n" +
            "   FROM departments\n" +
            "   WHERE id = ?1\n" +
            "\n" +
            "   UNION ALL\n" +
            "\n" +
            "   SELECT departments.id, departments.title, departments.master_id, departments.master_department_id, departments.created, departments.updated\n" +
            "   FROM departments\n" +
            "      JOIN r\n" +
            "          ON departments.master_department_id = r.id\n" +
            ")\n" +
            "\n" +
            "SELECT * FROM r;"
            , nativeQuery = true)
    List<Department> getSubordinateDepartments(Long id);
}
