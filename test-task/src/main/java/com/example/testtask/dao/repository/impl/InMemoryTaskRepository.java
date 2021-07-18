package com.example.testtask.dao.repository.impl;

import com.example.testtask.dao.entity.TaskEntity;
import com.example.testtask.dao.repository.TaskRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
@ConditionalOnProperty(value = "datasource.implementation", havingValue = "in-memory")
public class InMemoryTaskRepository implements TaskRepository {

    private final ConcurrentHashMap<Long, TaskEntity> storage = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    @Override
    public TaskEntity saveTask(TaskEntity taskEntity) {
        long id = counter.getAndIncrement();
        taskEntity.setId(id);
        taskEntity.setLastModificationDate(LocalDateTime.now());
        storage.put(id, taskEntity);
        return storage.get(id);
    }

    @Override
    public Optional<TaskEntity> findTaskById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public TaskEntity updateTask(TaskEntity taskEntity) {
        return storage.computeIfPresent(taskEntity.getId(), (k, v) -> {
            v.setDescription(taskEntity.getDescription());
            v.setName(taskEntity.getName());
            v.setLastModificationDate(LocalDateTime.now());
            return v;
        });
    }

    @Override
    public Page<TaskEntity> findAllTasks(Pageable pageable) {
        List<TaskEntity> tasks = storage.values()
                .stream()
                .sorted(Comparator.comparing(TaskEntity::getLastModificationDate))
                .collect(Collectors.toList());
        return new PageImpl<>(tasks, pageable, tasks.size());

    }

    @Override
    public void deleteTaskById(Long id) {
        storage.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return storage.containsKey(id);
    }
}
