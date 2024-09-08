package com.example.tasksandusers.service

import com.example.tasksandusers.exception.TaskNotFoundException
import com.example.tasksandusers.exception.UserNotFoundException
import com.example.tasksandusers.mapper.TaskMapper
import com.example.tasksandusers.model.dto.TaskDTO
import com.example.tasksandusers.model.entity.Task
import com.example.tasksandusers.repository.TaskRepository
import com.example.tasksandusers.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class TaskService(private val taskRepository: TaskRepository, private val userRepository: UserRepository, private val taskMapper: TaskMapper) {
    // Получаем список всех задач
    fun getAllTasks(): List<Task> = taskRepository.findAll()

    //Получаем задачу по taskId
    fun getTaskById(taskId: Long): TaskDTO = taskMapper.toDTO(taskRepository.getReferenceById(taskId))
        ?: throw TaskNotFoundException(HttpStatus.NOT_FOUND, "Задача с $taskId не найдена")

    //Создаем задачу
    fun createTask(task: TaskDTO): Long? {
        val result: Task = taskMapper.taskDTOToTask(task)
        return taskRepository.save(result).id
    }

    //Обновляем задачу, если она найдена, если нет выбрасываем исключение
    fun updateTask(taskId: Long, task: TaskDTO): TaskDTO {
        val taskFromDB: Task = taskRepository.findById(taskId).get();
        val result: Task = taskRepository.save(taskMapper.updateTask(task, taskFromDB))
        return taskMapper.toDTO(result)
    }

    //Удаляем задачу
    fun deleteTaskById(taskId: Long) {
        return if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId)
        } else throw TaskNotFoundException(HttpStatus.NOT_FOUND, "Задача с $taskId не найдена")
    }

    fun changeUser(taskDTO: TaskDTO): Task {
        return if (userRepository.existsById(taskDTO.userId)) {
            taskRepository.save(
                Task(
                    id = taskDTO.id,
                    name = taskDTO.name,
                    description = taskDTO.description,
                    date = taskDTO.date,
                    user = userRepository.getReferenceById(taskDTO.userId)
                )
            )
        } else throw UserNotFoundException(HttpStatus.NOT_FOUND, "Задача с ${taskDTO.userId} не найдена")
    }
}