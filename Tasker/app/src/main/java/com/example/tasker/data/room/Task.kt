package com.example.tasker.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    val title: String,
    val projectName: String,
    val deadline: String,
    val isCompleted: Boolean,
)
