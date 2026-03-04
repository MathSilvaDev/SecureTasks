package com.msilva.secureList.task.service;

import com.msilva.secureList.task.dto.request.CreateTaskRequest;
import com.msilva.secureList.task.dto.response.TaskResponse;
import com.msilva.secureList.task.entity.Task;
import com.msilva.secureList.task.repository.TaskRepository;
import com.msilva.secureList.user.entity.User;
import com.msilva.secureList.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public List<TaskResponse> findAllByUser(String authEmail){
        User user = findUserByEmail(authEmail);

        return taskRepository.findAllByUser(user)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public TaskResponse create(String authEmail, CreateTaskRequest request){
        User user = findUserByEmail(authEmail);

        Task task = new Task(
                request.title(),
                request.description(),
                user
        );
        taskRepository.save(task);

        return toResponse(task);
    }

    @Transactional
    public void toggleCompleted(Long id, String authEmail){
        User user = findUserByEmail(authEmail);
        Task task = findTaskByIdAndUser(id, user);

        task.toggleCompleted();
    }

    @Transactional
    public void deleteByIdAndUser(Long id, String authEmail){
        User user = findUserByEmail(authEmail);
        Task task = findTaskByIdAndUser(id, user);

        taskRepository.delete(task);
    }

    private TaskResponse toResponse(Task task){
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted(),
                task.getCreatedAt()
        );
    }

    private User findUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not Found"));
    }

    private Task findTaskByIdAndUser(Long id, User user){
        return taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
