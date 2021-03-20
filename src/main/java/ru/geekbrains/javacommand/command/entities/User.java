/*
 * License Headers.
 */

package ru.geekbrains.javacommand.command.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * @author Igor Popovich, email: popovichia@gmail.com
 */

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "users")
public class User extends DefaultEntity {

    @Column(name = "username", nullable = false, unique = true)
    @Length(min = 3, max = 50)
    private String userName;

    @Column(name = "password", nullable = false)
    @Length(min = 8, max = 64)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> listRoles;

}



