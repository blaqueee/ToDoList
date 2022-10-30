package edu.personal.issuetracker.service;

import edu.personal.issuetracker.domain.Task;
import edu.personal.issuetracker.domain.User;
import edu.personal.issuetracker.dto.TaskDto;
import edu.personal.issuetracker.dto.form.TaskForm;
import edu.personal.issuetracker.mapper.TaskMapper;
import edu.personal.issuetracker.repository.TaskRepository;
import edu.personal.issuetracker.utility.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;

    public TaskDto createTask(TaskForm taskForm, Authentication auth) {
        long count = taskRepository.count();
        String fileName = FileUtil.writeFile(taskForm.getFile(), count + 2, auth.getName());
        User user = (User) auth.getPrincipal();
        Task task = taskMapper.mapToTask(taskForm, user, fileName);
        Task newTask = taskRepository.save(task);
        return taskMapper.mapToTaskDto(newTask);
    }
}
