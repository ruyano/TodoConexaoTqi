package com.example.todoconexaotqi.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Task(
    val description: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)