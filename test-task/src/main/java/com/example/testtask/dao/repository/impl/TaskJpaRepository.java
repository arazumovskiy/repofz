package com.example.testtask.dao.repository.impl;

import com.example.testtask.dao.entity.TaskEntity;
import com.example.testtask.dao.repository.TaskRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@ConditionalOnProperty(value = "datasource.implementation", havingValue = "persistence")
public interface TaskJpaRepository extends TaskRepository, JpaRepository<TaskEntity, Long> {

    @Override
    default TaskEntity saveTask(TaskEntity taskEntity) {
        return save(taskEntity);
    }

    @Override
    default Optional<TaskEntity> findTaskById(Long id) {
        return findById(id);
    }

    @Override
    default TaskEntity updateTask(TaskEntity taskEntity) {
        return save(taskEntity);
    }

    @Override
    default Page<TaskEntity> findAllTasks(Pageable pageable) {
        return findAll(pageable);
    }

    @Override
    default void deleteTaskById(Long id) {
        deleteById(id);
    }

}
