package edu.personal.issuetracker.mapper;

import edu.personal.issuetracker.domain.Task;
import edu.personal.issuetracker.domain.User;
import edu.personal.issuetracker.dto.TaskDto;
import edu.personal.issuetracker.dto.form.TaskForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TaskMapper {
    private final UserMapper userMapper;

    public TaskDto mapToTaskDto(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .fileName(task.getFileName())
                .time(task.getTime())
                .owner(userMapper.mapToUserDto(task.getOwner()))
                .build();
    }

    public Task mapToTask(TaskForm taskForm, User user, String fileName) {
        return Task.builder()
                .title(taskForm.getTitle())
                .description(taskForm.getDescription())
                .fileName(fileName)
                .time(LocalDateTime.now())
                .owner(user)
                .build();
    }
}
