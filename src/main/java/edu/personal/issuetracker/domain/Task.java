package edu.personal.issuetracker.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @JsonProperty("file_name")
    private String fileName;
    private LocalDateTime time;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;
    @Enumerated(EnumType.STRING)
    private Status status;
}
