package com.msilva.secureList.domain.task.repository;

import com.msilva.secureList.domain.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByIdAndUserId(Long id, UUID userId);

    List<Task> findAllByUserId(UUID userId);
}
