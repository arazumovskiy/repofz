package com.example.testtask.service.impl;

import com.example.testtask.exception.EntityNotFoundException;
import com.example.testtask.dao.repository.TaskRepository;
import com.example.testtask.dto.TaskDto;
import com.example.testtask.mapper.TaskMapper;
import com.example.testtask.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;


    @Override
    public TaskDto saveTask(TaskDto taskDto) {
        return taskMapper.mapEntityToDto(taskRepository.saveTask(taskMapper.mapDtoToEntity(taskDto)));
    }

    @Override
    public TaskDto findTaskById(Long id) {
        return taskMapper.mapEntityToDto(taskRepository.findTaskById(id)
                .orElseThrow(() -> EntityNotFoundException.getInstanceByEntityNameAndId("Task", id)));
    }

    @Override
    public TaskDto updateTask(TaskDto taskDto) {
        if (!taskRepository.existsById(taskDto.getId())) {
            throw EntityNotFoundException.getInstanceByEntityNameAndId("Task", taskDto.getId());
        }
        return taskMapper.mapEntityToDto(taskRepository.updateTask(taskMapper.mapDtoToEntity(taskDto)));
    }

    @Override
    public Page<TaskDto> findAllTasks(Pageable pageable) {
        return taskRepository.findAllTasks(pageable).map(taskMapper::mapEntityToDto);
    }

    @Override
    public void deleteTaskById(Long id) {
        if (!taskRepository.existsById(id)) {
            throw EntityNotFoundException.getInstanceByEntityNameAndId("Task", id);
        }
        taskRepository.deleteTaskById(id);
    }
}
