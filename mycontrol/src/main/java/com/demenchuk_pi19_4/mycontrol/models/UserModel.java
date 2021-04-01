package com.demenchuk_pi19_4.mycontrol.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * The type User model.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class UserModel extends MySuperModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthday;

    @OneToMany(mappedBy = "user")
    private List<TaskModel> tasks;
}
