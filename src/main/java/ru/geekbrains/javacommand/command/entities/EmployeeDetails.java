package ru.geekbrains.javacommand.command.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "employees_details")
public class EmployeeDetails extends DefaultEntity{

    @Column(name = "mail")
    private String mail;

    @OneToOne
    @JoinColumn(name = "employee_id", nullable = false)
    @JsonBackReference
    private Employee employee;
}
