package com.example.tasksandusers.mapper

import com.example.tasksandusers.model.dto.TaskDTO
import com.example.tasksandusers.model.entity.Task
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget

@Mapper
interface TaskMapper {
    fun taskDTOToTask(taskDTO: TaskDTO?): Task
    fun toDTO(task: Task?): TaskDTO
    fun updateTask(dto: TaskDTO?, @MappingTarget task: Task?): Task
}