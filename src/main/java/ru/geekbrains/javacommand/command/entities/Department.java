package ru.geekbrains.javacommand.command.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "departments")
public class Department extends DefaultEntity {

    @Column(name = "title")
    private String title;

    @OneToOne
    @JoinColumn(name = "master_id", referencedColumnName = "id")
    private Employee master;

    @OneToOne
    @JoinColumn(name = "master_department_id", referencedColumnName = "id")
    private Department masterDepartment;

    @OneToMany
		@JoinColumn(name = "department_id")
    private List<Employee> employees;

}