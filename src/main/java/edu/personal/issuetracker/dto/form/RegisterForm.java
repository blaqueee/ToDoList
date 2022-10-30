package edu.personal.issuetracker.dto.form;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterForm {
    private String username;
    private String email;
    private String password;
}
