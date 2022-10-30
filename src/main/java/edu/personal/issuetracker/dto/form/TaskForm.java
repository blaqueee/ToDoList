package edu.personal.issuetracker.dto.form;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskForm implements Serializable {
    private String title;
    private String description;
    private MultipartFile file;
}
