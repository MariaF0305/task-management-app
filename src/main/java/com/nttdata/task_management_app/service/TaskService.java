package com.nttdata.task_management_app.service;

import com.nttdata.task_management_app.domain.Task;

import java.util.Optional;
import java.util.Set;

public interface TaskService {
    Iterable<Task> findAllTasks();
    Optional<Task> findTaskById(Long taskId);
    Set<Task> findTasksByOwnerId(Long ownerId);
    Task saveTask(Task task);
    void deleteTaskById(Long taskId);
    Task updateTaskRemainingEffort(Long taskId, int remainingEffort);
}
