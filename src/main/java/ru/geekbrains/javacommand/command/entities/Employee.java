package ru.geekbrains.javacommand.command.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "employees")
public class Employee extends DefaultEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "position_id")
    private Position position;

    @ManyToOne(optional = false)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade =  CascadeType.PERSIST, mappedBy = "employee")
    private EmployeeDetails employeeDetails;


    public String getFIO(){
        return String.format("%s %s %s", this.getLastName(), this.getFirstName(), this.getMiddleName());
    }

}
