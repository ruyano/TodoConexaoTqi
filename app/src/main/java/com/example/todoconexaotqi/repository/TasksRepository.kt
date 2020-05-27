package com.example.todoconexaotqi.repository

import androidx.lifecycle.LiveData
import com.example.todoconexaotqi.database.TaskDao
import com.example.todoconexaotqi.model.Task

class TasksRepository(private val taskDao: TaskDao) {

    fun create(task: Task) {
        taskDao.create(task)
    }

    val readAll: LiveData<List<Task>> = taskDao.readAll()

    fun update(task: Task) {
        taskDao.update(task)
    }

    fun delete(task: Task) {
        taskDao.delete(task)
    }

}