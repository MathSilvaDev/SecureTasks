package com.msilva.secureList.task.controller;

import com.msilva.secureList.task.dto.response.TaskResponse;
import com.msilva.secureList.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskResponse>> findAllByUser(Authentication auth){
        return ResponseEntity
                .ok(taskService.findAllByUser(auth.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByIdAndUser(@PathVariable Long id,
                                                  Authentication auth){
        taskService.deleteByIdAndUser(id, auth.getName());
        return ResponseEntity.noContent().build();
    }
}
