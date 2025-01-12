package com.nttdata.task_management_app.service.implementation;

import com.nttdata.task_management_app.domain.Task;
import com.nttdata.task_management_app.repositories.TaskRepository;
import com.nttdata.task_management_app.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class TaskServiceImplementation implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImplementation(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Iterable<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> findTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }

    @Override
    public Set<Task> findTasksByOwnerId(Long ownerId) {
        return taskRepository.findByOwnerId(ownerId);
    }

    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTaskById(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public Task updateTaskRemainingEffort(Long taskId, int remainingEffort) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setRemainingEffort(remainingEffort);
        return taskRepository.save(task);
    }
}
