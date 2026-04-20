package com.msilva.secureList.application.task.controller;

import com.msilva.secureList.application.task.dto.request.CreateTaskRequest;
import com.msilva.secureList.application.task.dto.response.TaskResponse;
import com.msilva.secureList.application.task.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    private UUID getAuthUserId(Authentication auth){
        return UUID.fromString(
                ((Jwt)auth.getPrincipal()).getClaim("userId")
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskResponse>> findAllByUserId(Authentication auth){

        UUID userId = getAuthUserId(auth);

        return ResponseEntity
                .ok(taskService.findAllByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(Authentication auth,
                                               @Valid @RequestBody CreateTaskRequest request){
        UUID userId = getAuthUserId(auth);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(taskService.create(userId, request));
    }

    @PatchMapping("/{id}/completed")
    public ResponseEntity<Void> toggleCompleted(@PathVariable Long id,
                                                Authentication auth){
        UUID userId = getAuthUserId(auth);

        taskService.toggleCompleted(id, userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByIdAndUserId(@PathVariable Long id,
                                                    Authentication auth){
        UUID userId = getAuthUserId(auth);

        taskService.deleteByIdAndUserId(id, userId);
        return ResponseEntity.noContent().build();
    }
}
