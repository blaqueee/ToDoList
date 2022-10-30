package edu.personal.issuetracker.service;

import edu.personal.issuetracker.domain.Status;
import edu.personal.issuetracker.domain.Task;
import edu.personal.issuetracker.domain.User;
import edu.personal.issuetracker.dto.TaskDto;
import edu.personal.issuetracker.dto.form.TaskEditForm;
import edu.personal.issuetracker.dto.form.TaskForm;
import edu.personal.issuetracker.mapper.TaskMapper;
import edu.personal.issuetracker.repository.TaskRepository;
import edu.personal.issuetracker.utility.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;

    public TaskDto createTask(TaskForm taskForm, Authentication auth) {
        long count = taskRepository.count();
        String fileName = FileUtil.writeFile(taskForm.getFile(), count + 1, auth.getName());
        User user = (User) auth.getPrincipal();
        Task task = taskMapper.mapToTask(taskForm, user, fileName);
        Task newTask = taskRepository.save(task);
        return taskMapper.mapToTaskDto(newTask);
    }

    public ResponseEntity<?> finishTask(Long taskId, Authentication auth) {
        User owner = (User) auth.getPrincipal();
        if (!taskRepository.existsById(taskId) || !taskRepository.existsByIdAndOwner(taskId, owner))
            return ResponseEntity.badRequest().body("Task with ID " + taskId + " doesn't exists or it's not yours!");
        Task task = taskRepository.findById(taskId).get();
        return task.getStatus().finish(task, taskRepository, taskMapper);
    }

    public ResponseEntity<?> deleteTask(Long taskId, Authentication auth) {
        User owner = (User) auth.getPrincipal();
        if (!taskRepository.existsById(taskId) || !taskRepository.existsByIdAndOwner(taskId, owner))
            return ResponseEntity.badRequest().body("Task with ID " + taskId + " doesn't exists or it's not yours!");
        Task task = taskRepository.findById(taskId).get();
        return task.getStatus().delete(task, taskRepository, taskMapper);
    }

    public ResponseEntity<?> clearTrash(Authentication auth) {
        User owner = (User) auth.getPrincipal();
        var tasks = taskRepository.findAllByOwnerAndStatus(owner, Status.TRASH);
        taskRepository.deleteAll(tasks);
        return ResponseEntity.ok("Trash has been successfully cleared!");
    }

    public ResponseEntity<?> editTask(TaskEditForm taskEditForm, Authentication auth) {
        User owner = (User) auth.getPrincipal();
        Long taskId = taskEditForm.getId();
        if (!taskRepository.existsById(taskId) || !taskRepository.existsByIdAndOwner(taskId, owner))
            return ResponseEntity.badRequest().body("Task with ID " + taskId + " doesn't exists or it's not yours!");
        return editAndSaveTask(taskEditForm, auth);
    }

    private ResponseEntity<TaskDto> editAndSaveTask(TaskEditForm taskEditForm, Authentication auth) {
        Task task = taskRepository.findById(taskEditForm.getId()).get();
        String fileName = null;
        if (taskEditForm.getFile() != null)
            fileName = FileUtil.writeFile(taskEditForm.getFile(), taskEditForm.getId(), auth.getName());
        task = taskMapper.mapToTask(task, taskEditForm, fileName);
        Task editedTask = taskRepository.save(task);
        return ResponseEntity.ok(taskMapper.mapToTaskDto(editedTask));
    }
}
