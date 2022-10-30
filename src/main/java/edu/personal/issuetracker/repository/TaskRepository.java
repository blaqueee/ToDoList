package edu.personal.issuetracker.repository;

import edu.personal.issuetracker.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
