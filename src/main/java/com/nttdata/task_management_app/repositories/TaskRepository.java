package com.nttdata.task_management_app.repositories;

import com.nttdata.task_management_app.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface TaskRepository extends CrudRepository<Task, Long> {
    Set<Task> findByOwnerId(Long ownerId);
}
