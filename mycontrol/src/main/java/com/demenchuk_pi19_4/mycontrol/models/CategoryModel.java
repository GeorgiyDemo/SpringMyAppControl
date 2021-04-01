package com.demenchuk_pi19_4.mycontrol.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * The type Category model.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "categories")
public class CategoryModel extends MySuperModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "tasks_categories",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private Set<TaskModel> tasks;
}