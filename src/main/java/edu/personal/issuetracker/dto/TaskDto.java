package edu.personal.issuetracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    @JsonProperty("file_name")
    private String fileName;
    private LocalDateTime time;
    private UserDto owner;
}
