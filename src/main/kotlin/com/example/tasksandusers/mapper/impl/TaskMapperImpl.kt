package com.example.tasksandusers.mapper.impl

import com.example.tasksandusers.exception.ServiceException
import com.example.tasksandusers.mapper.TaskMapper
import com.example.tasksandusers.model.dto.TaskDTO
import com.example.tasksandusers.model.entity.Task
import com.example.tasksandusers.model.entity.User
import org.springframework.stereotype.Component

@Component
class TaskMapperImpl : TaskMapper {

    override fun taskDTOToTask(taskDTO: TaskDTO?, userToSave: User): Task {
        if (taskDTO == null) throw ServiceException("Сервисная ошибка")
        if (taskDTO.name.isEmpty()) throw ServiceException("Имя не должно быть пустым")
        return Task(
            id = null,
            name = taskDTO.name,
            description = taskDTO.description,
            date = taskDTO.date,
            user = userToSave
        )
    }

    override fun toDTO(task: Task?): TaskDTO {
        if (task == null) throw ServiceException("Сервисная ошибка")
        return TaskDTO(
            id = task.id!!,
            name = task.name,
            description = task.description,
            date = task.date,
        )
    }

    override fun updateTask(taskDTO: TaskDTO, task: Task?): Task {
        if (task == null) throw ServiceException("Сервисная ошибка")
        return Task(
            id = task.id,
            name = taskDTO.name,
            description = taskDTO.description,
            date = taskDTO.date,
            user = task.user
        )
    }

    override fun changeUser(task: Task, userToChange: User): Task {
        return Task(
            id = task.id,
            name = task.name,
            description = task.description,
            date = task.date,
            user = userToChange
        )
    }
}