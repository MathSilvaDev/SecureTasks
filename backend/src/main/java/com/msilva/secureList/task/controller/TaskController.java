package com.msilva.secureList.task.controller;

import com.msilva.secureList.task.dto.request.CreateTaskRequest;
import com.msilva.secureList.task.dto.response.TaskResponse;
import com.msilva.secureList.task.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/all")
    public ResponseEntity<List<TaskResponse>> findAllByUser(Authentication auth){
        return ResponseEntity
                .ok(taskService.findAllByUser(auth.getName()));
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(Authentication auth,
                                               @Valid @RequestBody CreateTaskRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(taskService.create(auth.getName(), request));
    }

    @PatchMapping("/{id}/completed")
    public ResponseEntity<Void> toggleCompleted(@PathVariable Long id,
                                                Authentication auth){
        taskService.toggleCompleted(id, auth.getName());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByIdAndUser(@PathVariable Long id,
                                                  Authentication auth){
        taskService.deleteByIdAndUser(id, auth.getName());
        return ResponseEntity.noContent().build();
    }
}
