package com.msilva.secureList.task.service;

import com.msilva.secureList.task.dto.response.TaskResponse;
import com.msilva.secureList.task.entity.Task;
import com.msilva.secureList.task.repository.TaskRepository;
import com.msilva.secureList.user.entity.User;
import com.msilva.secureList.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
}
