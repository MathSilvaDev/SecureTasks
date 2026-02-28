package com.msilva.secureList.task.repository;

import com.msilva.secureList.task.entity.Task;
import com.msilva.secureList.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByIdAndUser(Long id, User user);

    List<Task> findAllByUser(User user);
}
