package edu.personal.issuetracker.repository;

import edu.personal.issuetracker.domain.Status;
import edu.personal.issuetracker.domain.Task;
import edu.personal.issuetracker.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    boolean existsByIdAndOwner(Long id, User user);
    List<Task> findAllByOwnerAndStatus(User owner, Status status);
}
