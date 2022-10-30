package edu.personal.issuetracker.controller;

import edu.personal.issuetracker.dto.form.TaskForm;
import edu.personal.issuetracker.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<?> createTask(@ModelAttribute TaskForm taskForm, Authentication auth) {
        return ResponseEntity.ok(taskService.createTask(taskForm, auth));
    }
}
