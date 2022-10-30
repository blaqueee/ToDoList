package edu.personal.issuetracker.controller;

import edu.personal.issuetracker.dto.form.RegisterForm;
import edu.personal.issuetracker.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
    private final MainService mainService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterForm registerForm) {
        return mainService.register(registerForm);
    }

}
