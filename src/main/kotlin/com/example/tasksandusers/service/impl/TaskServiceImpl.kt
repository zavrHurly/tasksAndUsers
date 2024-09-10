package com.example.tasksandusers.service.impl

import com.example.tasksandusers.exception.TaskNotFoundException
import com.example.tasksandusers.mapper.impl.TaskMapperImpl
import com.example.tasksandusers.model.dto.TaskDTO
import com.example.tasksandusers.model.entity.Task
import com.example.tasksandusers.model.entity.User
import com.example.tasksandusers.repository.TaskRepository
import com.example.tasksandusers.repository.UserRepository
import com.example.tasksandusers.service.TaskService
import com.example.tasksandusers.util.UserUtils
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TaskServiceImpl(
    private val taskRepository: TaskRepository,
    private val userRepository: UserRepository,
    private val taskMapper: TaskMapperImpl
) : TaskService {
    // Получаем список всех задач
    override fun getAllTasks(pageable: Pageable): Page<TaskDTO> {
        val id: Long = UserUtils.getCurrentUserId()
        val tasks: Page<Task> = taskRepository.findAllByUserId(id, pageable)
        return tasks.map { taskMapper.toDTO(it) }
    }

    //Получаем задачу по taskId
    override fun getTaskById(taskId: Long): TaskDTO {
        val id: Long = UserUtils.getCurrentUserId()
        val task: Task? = taskRepository.findByIdAndUserId(taskId, id)
        return if (task != null) {
            taskMapper.toDTO(task)
        } else {
            throw TaskNotFoundException(taskId)
        }
    }


    //Создаем задачу
    override fun createTask(task: TaskDTO): Long? {
        val user: User = UserUtils.getCurrentUser()
        return taskRepository.save(taskMapper.taskDTOToTask(task, user)).id
    }

    //Обновляем задачу, если она найдена, если нет выбрасываем исключение
    override fun updateTask(taskId: Long, task: TaskDTO): TaskDTO {
        val taskFromDB: Task = taskRepository.findByIdAndUserId(taskId, UserUtils.getCurrentUserId())
            ?: throw TaskNotFoundException(taskId)
        val result: Task = taskRepository.save(taskMapper.updateTask(task, taskFromDB))
        return taskMapper.toDTO(result)
    }

    //Удаляем задачу
    override fun deleteTaskById(taskId: Long) {
        val taskFromDB: Task? = taskRepository.findByIdAndUserId(taskId, UserUtils.getCurrentUserId())
        if (taskFromDB != null) {
            taskRepository.delete(taskFromDB)
        }
    }

    override fun changeUser(taskId: Long, userId: Long): TaskDTO {
        val user: User = userRepository.findById(userId).get()
        val task: Task? = taskRepository.findByIdAndUserId(taskId, UserUtils.getCurrentUserId())
        return if (task != null) {
            taskMapper.toDTO(taskRepository.save(taskMapper.changeUser(task, user)))
        } else {
            throw TaskNotFoundException(taskId)
        }
    }
}