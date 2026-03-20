package com.msilva.secureList.task.service;

import com.msilva.secureList.task.dto.response.TaskResponse;
import com.msilva.secureList.task.entity.Task;
import com.msilva.secureList.task.factory.TestDataFactory;
import com.msilva.secureList.task.repository.TaskRepository;
import com.msilva.secureList.user.entity.User;
import com.msilva.secureList.user.repository.UserRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
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


}