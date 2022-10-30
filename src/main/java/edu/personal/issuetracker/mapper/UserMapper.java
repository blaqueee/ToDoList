package edu.personal.issuetracker.mapper;

import edu.personal.issuetracker.domain.User;
import edu.personal.issuetracker.dto.form.RegisterForm;
import edu.personal.issuetracker.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final PasswordEncoder encoder;

    @Autowired
    public UserMapper(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public User mapToUser(RegisterForm registerForm) {
        return User.builder()
                .username(registerForm.getUsername())
                .email(registerForm.getEmail())
                .password(encoder.encode(registerForm.getPassword()))
                .enabled(true)
                .build();
    }

    public UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
