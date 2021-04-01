package com.demenchuk_pi19_4.mycontrol.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * The type Task model.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "tasks")
public class TaskModel extends MySuperModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titleName;
    private String description;
    private Boolean isTaskReady;
    private LocalDateTime completedDateTime;

    @ManyToMany(mappedBy = "tasks")
    private Set<CategoryModel> categories;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;
}

