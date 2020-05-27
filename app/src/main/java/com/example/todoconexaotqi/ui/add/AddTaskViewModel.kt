package com.example.todoconexaotqi.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todoconexaotqi.database.AppDatabase
import com.example.todoconexaotqi.model.Task
import com.example.todoconexaotqi.repository.TasksRepository

class AddTaskViewModel(application: Application): AndroidViewModel(application) {

    private val repository: TasksRepository

    var description = MutableLiveData<String>()

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    private var _shoudCloseActivity = MutableLiveData<Boolean>()
    var shouldCloseActivity: LiveData<Boolean> = _shoudCloseActivity

    init {
        val taskDao = AppDatabase.getDatabase(getApplication()).taskDao()
        repository = TasksRepository(taskDao)
    }

    fun addTask() {
        if (description.value.isNullOrEmpty()) {
            _errorMessage.postValue("A descrição não pode ser vazia")
        } else {
            description.value?.let {
                Thread(Runnable {
                    repository.create(Task(it))
                }).start()
            }
            _errorMessage.postValue("Task inserida com sucesso!")
            _shoudCloseActivity.postValue(true)
        }
    }

}