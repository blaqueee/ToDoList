package edu.personal.issuetracker.controller;

import edu.personal.issuetracker.dto.form.TaskForm;
import edu.personal.issuetracker.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<?> createTask(@ModelAttribute TaskForm taskForm, Authentication auth) {
        return ResponseEntity.ok(taskService.createTask(taskForm, auth));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<?> finishTask(@PathVariable Long taskId, Authentication auth) {
        return taskService.finishTask(taskId, auth);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId, Authentication auth) {
        return taskService.deleteTask(taskId, auth);
    }

    @DeleteMapping
    public ResponseEntity<?> clearTrash(Authentication auth) {
        return taskService.clearTrash(auth);
    }
}
