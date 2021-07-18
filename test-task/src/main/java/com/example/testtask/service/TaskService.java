package com.example.testtask.service;

import com.example.testtask.dto.TaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {

    TaskDto saveTask(TaskDto taskDto);

    TaskDto findTaskById(Long id);

    TaskDto updateTask(TaskDto taskDto);

    Page<TaskDto> findAllTasks(Pageable pageable);

    void deleteTaskById(Long id);
}
