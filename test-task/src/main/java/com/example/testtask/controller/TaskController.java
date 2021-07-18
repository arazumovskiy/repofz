package com.example.testtask.controller;

import com.example.testtask.dto.ResponseDto;
import com.example.testtask.dto.TaskDto;
import com.example.testtask.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(taskService.findTaskById(id));
    }

    @GetMapping
    public ResponseEntity<Page<TaskDto>> getAllTasksInPage(@RequestParam(defaultValue = "0") @Positive int pageNum,
                                                           @RequestParam(defaultValue = "10") @Positive @Max(500) int pageSize) {
        return ResponseEntity.ok(taskService.findAllTasks(PageRequest.of(pageNum, pageSize, Sort.Direction.ASC, "lastModificationDate")));
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@Validated(TaskDto.New.class) @RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.saveTask(taskDto));
    }

    @PutMapping
    public ResponseEntity<TaskDto> updateTask(@Validated(TaskDto.Update.class) @RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.updateTask(taskDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteTaskById(@PathVariable("id") Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.ok(new ResponseDto(String.format("Task with id=%s was successfully deleted", id)));
    }
}
