package ru.geekbrains.javacommand.command.entities;

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
    private Employee employee;
}
