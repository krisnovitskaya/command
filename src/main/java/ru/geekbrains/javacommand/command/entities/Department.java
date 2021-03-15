package ru.geekbrains.javacommand.command.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "departments")
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "master_id")
    private Employee employee;

    @Column(name = "master_department_id")
    private Department department;



    @OneToMany(cascade = CascadeType.ALL, mappedBy ="department" )
    private List<Employee> employees;

}