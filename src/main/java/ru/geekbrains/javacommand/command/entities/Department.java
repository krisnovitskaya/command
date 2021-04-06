package ru.geekbrains.javacommand.command.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Employee master;

    @OneToOne
    @JoinColumn(name = "master_department_id", referencedColumnName = "id")
    @JsonIgnore
    private Department masterDepartment;

    @OneToMany
		@JoinColumn(name = "department_id")
    private List<Employee> employees;

}