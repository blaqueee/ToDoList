package edu.personal.issuetracker.domain;

import edu.personal.issuetracker.mapper.TaskMapper;
import edu.personal.issuetracker.repository.TaskRepository;
import org.springframework.http.ResponseEntity;

public enum Status {
    NOT_FINISHED {
        @Override
        public ResponseEntity<?> finish(Task task, TaskRepository taskRepository, TaskMapper taskMapper) {
            task.setStatus(FINISHED);
            Task updatedTask = taskRepository.save(task);
            return ResponseEntity.ok(taskMapper.mapToTaskDto(updatedTask));
        }

        @Override
        public ResponseEntity<?> delete(Task task, TaskRepository taskRepository, TaskMapper taskMapper) {
            task.setStatus(TRASH);
            Task deletedTask = taskRepository.save(task);
            return ResponseEntity.ok(taskMapper.mapToTaskDto(deletedTask));
        }
    },
    FINISHED {
        @Override
        public ResponseEntity<?> finish(Task task, TaskRepository taskRepository, TaskMapper taskMapper) {
            return ResponseEntity.badRequest().body("Task with ID " + task.getId() + " is already FINISHED!");
        }

        @Override
        public ResponseEntity<?> delete(Task task, TaskRepository taskRepository, TaskMapper taskMapper) {
            task.setStatus(TRASH);
            Task deletedTask = taskRepository.save(task);
            return ResponseEntity.ok(taskMapper.mapToTaskDto(deletedTask));
        }
    },
    TRASH {
        @Override
        public ResponseEntity<?> finish(Task task, TaskRepository taskRepository, TaskMapper taskMapper) {
            return ResponseEntity.badRequest().body("Task with ID " + task.getId() + " is in TRASH!");
        }

        @Override
        public ResponseEntity<?> delete(Task task, TaskRepository taskRepository, TaskMapper taskMapper) {
            taskRepository.delete(task);
            return ResponseEntity.ok("Task with ID " + task.getId() + " has been DELETED!");
        }
    };

    public abstract ResponseEntity<?> finish(Task task, TaskRepository taskRepository, TaskMapper taskMapper);
    public abstract ResponseEntity<?> delete(Task task, TaskRepository taskRepository, TaskMapper taskMapper);
}

