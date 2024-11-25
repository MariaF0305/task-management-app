package com.nttdata.task_management_app.controllers;

import com.nttdata.task_management_app.domain.Task;
import com.nttdata.task_management_app.repositories.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @RequestMapping("/all")
    public String getTasks(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());

        return "list-all-tasks";
    }

    @GetMapping("/by-owner/{ownerId}")
    public String getTasksByOwner(@PathVariable Long ownerId, Model model) {
        model.addAttribute("tasks", taskRepository.findByOwnerId(ownerId));

        return "list-tasks-by-owner";
    }

    @GetMapping("/{taskId}")
    public String getTaskById(@PathVariable Long taskId, Model model) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent()) {
            model.addAttribute("task", task.get());
            return "view-task";
        } else {
            model.addAttribute("error", "Task not found.");
            return "error-page";
        }
    }

    @GetMapping("/update-task-effort/{taskId}")
    public String showUpdateEffortForm(@PathVariable Long taskId, Model model) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isPresent()) {
            model.addAttribute("task", taskOptional.get());
            return "update-task-effort";
        } else {
            return "error";
        }
    }

    @PostMapping("/update-task-effort/{taskId}")
    public ResponseEntity<String> updateRemainingEffort(@PathVariable Long taskId, @RequestParam int remainingEffort) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setRemainingEffort(remainingEffort);
            taskRepository.save(task);
            return ResponseEntity.ok("Task effort updated successfully.");
        } else {
            return ResponseEntity.status(404).body("Task not found.");
        }
    }

    @DeleteMapping("/remove/{taskId}")
    public ResponseEntity<String> removeTask(@PathVariable Long taskId) {
        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
            return ResponseEntity.ok("Task removed successfully.");
        } else {
            return ResponseEntity.status(404).body("Task not found.");
        }
    }

}
