package edu.personal.issuetracker.repository;

import edu.personal.issuetracker.domain.Task;
import edu.personal.issuetracker.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    boolean existsByIdAndOwner(Long id, User user);
}
