package com.example.testtask.mapper;

import com.example.testtask.dao.entity.TaskEntity;
import com.example.testtask.dto.TaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDto mapEntityToDto(TaskEntity taskEntity);

    @Mapping(target = "lastModificationDate", ignore = true)
    TaskEntity mapDtoToEntity(TaskDto taskDto);
}
