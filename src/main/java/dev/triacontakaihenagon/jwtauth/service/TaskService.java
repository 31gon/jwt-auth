package dev.triacontakaihenagon.jwtauth.service;

import dev.triacontakaihenagon.jwtauth.dto.TaskRequest;
import dev.triacontakaihenagon.jwtauth.entity.Task;
import dev.triacontakaihenagon.jwtauth.entity.User;
import dev.triacontakaihenagon.jwtauth.exception.TaskNotFoundException;
import dev.triacontakaihenagon.jwtauth.exception.UserNotFoundException;
import dev.triacontakaihenagon.jwtauth.repository.TaskRepository;
import dev.triacontakaihenagon.jwtauth.repository.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Task createTask(TaskRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with " + request.getUserId() + " not found"));

        Task task = new Task(request);
        task.setUser(user);
        return taskRepository.save(task);
    }

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }
    public void deleteTask(Long id) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with " + id + " not found"));

        if (existingTask.getUser().getUserName().equals(getCurrentUsername())) taskRepository.deleteById(id);
        else throw new AccessDeniedException("You do not own this task");
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with "+ id +" not found "));

        if (!existingTask.getUser().getUserName().equals(getCurrentUsername())) {
            throw new AccessDeniedException("You do not own this task");
        }

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDone(updatedTask.isDone());
        return taskRepository.save(existingTask);
    }
    private String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
