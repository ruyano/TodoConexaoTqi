package com.example.todoconexaotqi.ui.main

import android.app.Application
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.todoconexaotqi.database.AppDatabase
import com.example.todoconexaotqi.model.Task
import com.example.todoconexaotqi.repository.TasksRepository

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository: TasksRepository
    val tasksList: LiveData<List<Task>>

    var listVisibility = ObservableField<Int>()
    var emptyViewVisibility = ObservableField<Int>()

    init {
        val taskDao = AppDatabase.getDatabase(getApplication()).taskDao()
        repository = TasksRepository(taskDao)
        tasksList = repository.readAll
    }

    fun getTaskAt(position: Int) = tasksList.value?.get(position)

    fun add() {
        Thread(Runnable {
            repository.create(Task("Task 1"))
        }).start()
    }

    fun onItemSelectedAt(position: Int) {
        getTaskAt(position)?.let {
            Thread(Runnable {
                repository.delete(it)
            }).start()
        }
    }

    fun updateVisibility() {
        if (tasksList.value?.isNullOrEmpty() == false) {
            showList()
        } else {
            showEmptyList()
        }
    }

    private fun showList() {
        listVisibility.set(VISIBLE)
        emptyViewVisibility.set(GONE)
    }

    private fun showEmptyList() {
        listVisibility.set(GONE)
        emptyViewVisibility.set(VISIBLE)
    }
}