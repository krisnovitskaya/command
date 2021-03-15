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

    @OneToOne
    @JoinColumn(name = "master_id", referencedColumnName = "id")
    private Employee master;

    @OneToOne
    @JoinColumn(name = "master_department_id", referencedColumnName = "id")
    private Department masterDepartment;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy ="department" )
    private List<Employee> employees;

}