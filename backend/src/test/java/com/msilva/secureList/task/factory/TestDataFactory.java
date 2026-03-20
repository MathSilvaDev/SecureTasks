package com.msilva.secureList.task.factory;

import com.msilva.secureList.task.dto.request.CreateTaskRequest;
import com.msilva.secureList.task.entity.Task;
import com.msilva.secureList.user.entity.User;

import java.util.ArrayList;
import java.util.List;

public class TestDataFactory {

    private static final String EMAIL = "test@test.test";

    public static User user(){
        return new User(
                EMAIL,
                "username",
                "password"
        );
    }

    public static Task task(User user){
        return new Task(
                "task-title",
                "task-description",
                user
        );
    }

    public static CreateTaskRequest createTaskRequest(){
        return new CreateTaskRequest(
                "task-title",
                "task-description"
        );
    }

    public static List<Task> taskList(User user, int quantity){

        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < quantity; i++){
            Task task = new Task(
                    ("task-" + i),
                    "task for test",
                    user
            );
            tasks.add(task);
        }

        return tasks;
    }
}
