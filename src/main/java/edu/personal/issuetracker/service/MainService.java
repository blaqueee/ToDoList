package edu.personal.issuetracker.service;

import edu.personal.issuetracker.domain.User;
import edu.personal.issuetracker.dto.form.RegisterForm;
import edu.personal.issuetracker.mapper.UserMapper;
import edu.personal.issuetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public ResponseEntity<?> register(RegisterForm registerForm) {
        User user = userMapper.mapToUser(registerForm);
        if (userRepository.existsUserByUsername(user.getUsername()) || userRepository.existsUserByEmail(user.getEmail()))
            return ResponseEntity.badRequest().body("User with this username or email already exists!");
        User newUser = userRepository.save(user);
        return ResponseEntity.ok(userMapper.mapToUserDto(newUser));
    }
}