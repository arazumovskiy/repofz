package com.example.testtask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Data
public class TaskDto {

    @Null(groups = {New.class}, message = "New Task id should not be specified")
    @NotNull(groups = {Update.class}, message = "id should be specified to update existing Task")
    private Long id;

    @NotBlank(groups = {New.class, Update.class}, message = "Task's name should not be empty")
    private String name;

    @NotBlank(groups = {New.class, Update.class}, message = "Task's description should not be empty")
    private String description;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime lastModificationDate;

    public interface New {
    }

    public interface Update {
    }
}
