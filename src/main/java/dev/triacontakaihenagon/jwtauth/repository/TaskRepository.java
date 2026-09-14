package dev.triacontakaihenagon.jwtauth.repository;

import dev.triacontakaihenagon.jwtauth.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTaskByDoneTrue();
}