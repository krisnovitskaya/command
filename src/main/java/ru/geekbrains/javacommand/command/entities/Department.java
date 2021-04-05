package ru.geekbrains.javacommand.command.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    private Employee master;

    @OneToOne
    @JoinColumn(name = "master_department_id", referencedColumnName = "id")
    @JsonBackReference
    private Department masterDepartment;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy ="department" )
    private List<Employee> employees;

}