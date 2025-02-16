package com.nttdata.task_management_app.controllers;

import com.nttdata.task_management_app.domain.Task;
import com.nttdata.task_management_app.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/task")
@PreAuthorize("isAuthenticated()")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping("/all")
    public String getTasks(Model model) {
        model.addAttribute("tasks", taskService.findAllTasks());
        return "list-all-tasks";
    }

    @GetMapping("/by-owner/{ownerId}")
    public String getTasksByOwner(@PathVariable Long ownerId, Model model) {
        model.addAttribute("tasks", taskService.findTasksByOwnerId(ownerId));
        return "list-tasks-by-owner";
    }

    @GetMapping("/{taskId}")
    public String getTaskById(@PathVariable Long taskId, Model model) {
        Optional<Task> task = taskService.findTaskById(taskId);
        if (task.isPresent()) {
            Task taskEntity = task.get();
            model.addAttribute("task", taskEntity);
            model.addAttribute("estimationAccuracy", taskEntity.computeEstimationAccuracy());
            return "view-task";
        } else {
            model.addAttribute("error", "Task not found.");
            return "error-page";
        }
    }

    @PostMapping("/update-task-effort/{taskId}")
    public ResponseEntity<String> updateRemainingEffort(@PathVariable Long taskId, @RequestParam int remainingEffort) {
        try {
            taskService.updateTaskRemainingEffort(taskId, remainingEffort);
            return ResponseEntity.ok("Task effort updated successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Task not found.");
        }
    }

    @DeleteMapping("/remove/{taskId}")
    public ResponseEntity<String> removeTask(@PathVariable Long taskId) {
        try {
            taskService.deleteTaskById(taskId);
            return ResponseEntity.ok("Task removed successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Task not found.");
        }
    }
}
