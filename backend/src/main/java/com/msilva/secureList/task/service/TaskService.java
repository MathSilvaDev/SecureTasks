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
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public List<TaskResponse> findAllByUser(UUID userId){

        return taskRepository.findAllByUserId(userId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public TaskResponse create(UUID userId, CreateTaskRequest request){

        User user = findUserById(userId);

        Task task = new Task(
                request.title(),
                request.description(),
                user
        );
        taskRepository.save(task);

        return toResponse(task);
    }

    @Transactional
    public void toggleCompleted(Long id, UUID userId){
        Task task = findTaskByIdAndUserId(id, userId);

        task.toggleCompleted();
    }

    @Transactional
    public void deleteByIdAndUser(Long id, UUID userId){
        Task task = findTaskByIdAndUserId(id, userId);

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

    private User findUserById(UUID userId){
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
                );
    }

    private Task findTaskByIdAndUserId(Long id, UUID userId){
        return taskRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
