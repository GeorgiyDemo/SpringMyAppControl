package com.demenchuk_pi19_4.mycontrol.repos;

import com.demenchuk_pi19_4.mycontrol.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Task repo.
 */
public interface TaskRepo extends JpaRepository<TaskModel, Long> {
}