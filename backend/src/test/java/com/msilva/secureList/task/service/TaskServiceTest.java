package com.msilva.secureList.task.service;

import com.msilva.secureList.application.task.dto.request.CreateTaskRequest;
import com.msilva.secureList.application.task.dto.response.TaskResponse;
import com.msilva.secureList.application.task.service.TaskService;
import com.msilva.secureList.domain.task.entity.Task;
import com.msilva.secureList.task.factory.TestDataFactory;
import com.msilva.secureList.domain.task.repository.TaskRepository;
import com.msilva.secureList.domain.user.entity.User;
import com.msilva.secureList.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TaskService taskService;

    @Nested
    class FindAll{

        @Test
        void shouldReturnAllTasksByUserId(){
            User user = TestDataFactory.user();

            UUID userId = user.getId();

            List<Task> tasks = TestDataFactory.taskList(user, 3);

            when(taskRepository.findAllByUserId(userId))
                    .thenReturn(tasks);

            List<TaskResponse> response = taskService.findAllByUserId(userId);

            assertEquals(tasks.size(), response.size());

        }
    }

    @Nested
    class Create{

        @Test
        void shouldCreateTaskSuccessfully(){

            CreateTaskRequest request = TestDataFactory.createTaskRequest();

            User user = TestDataFactory.user();
            UUID userId = user.getId();

            when(userRepository.findById(userId))
                    .thenReturn(Optional.of(user));

            TaskResponse response = taskService.create(userId, request);

            assertEquals(request.title(), response.title());
            assertEquals(request.description(), response.description());

            verify(userRepository).findById(userId);
            verify(taskRepository).save(any(Task.class));
        }

        @Test
        void shouldThrowWhenUserNotFound(){

            UUID userId = UUID.randomUUID();

            when(userRepository.findById(userId))
                    .thenReturn(Optional.empty());

            assertThrows(ResponseStatusException.class,
                    () -> taskService.create(userId, TestDataFactory.createTaskRequest()));
        }
    }

    @Nested
    class Delete{

        @Test
        void shouldDeleteTaskSuccessfully(){
            User user = TestDataFactory.user();
            Task task = TestDataFactory.task(user);

            Long id = task.getId();
            UUID userId = user.getId();

            when(taskRepository.findByIdAndUserId(id, userId))
                    .thenReturn(Optional.of(task));

            taskService.deleteByIdAndUserId(id, userId);

            verify(taskRepository).delete(task);
        }

    }
}