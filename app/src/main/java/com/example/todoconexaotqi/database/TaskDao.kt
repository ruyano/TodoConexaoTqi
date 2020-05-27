package com.example.todoconexaotqi.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todoconexaotqi.model.Task

@Dao
interface TaskDao {

    @Insert
    fun create(task: Task)

    @Query("SELECT * from Task")
    fun readAll(): LiveData<List<Task>>

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

}