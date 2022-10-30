package edu.personal.issuetracker.dto.form;

import edu.personal.issuetracker.domain.Status;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskEditForm {
    private Long id;
    private String title;
    private String description;
    private MultipartFile file;
    private Status status;
}
