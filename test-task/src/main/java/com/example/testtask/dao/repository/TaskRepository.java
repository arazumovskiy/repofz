package com.example.testtask.dao.repository;

import com.example.testtask.dao.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TaskRepository {

    TaskEntity saveTask(TaskEntity taskEntity);

    Optional<TaskEntity> findTaskById(Long id);

    TaskEntity updateTask(TaskEntity taskEntity);

    Page<TaskEntity> findAllTasks(Pageable pageable);

    void deleteTaskById(Long id);

    boolean existsById(Long id);
}
