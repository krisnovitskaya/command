package ru.geekbrains.javacommand.command.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonBackReference
    private Department department;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade =  CascadeType.PERSIST, mappedBy = "employee")
    private EmployeeDetails employeeDetails;

    //TODO тут этому не место
    public String getFIO(){
        return String.format("%s %s %s", this.getLastName(), this.getFirstName(), this.getMiddleName());
    }

}