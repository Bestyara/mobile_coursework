package com.example.tasker.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks WHERE projectName == :categoryName AND isCompleted = 0")
    fun getNotCompletedTasksFromCategory(categoryName: String): List<Task>

    @Query("SELECT * FROM tasks WHERE isCompleted == 1")
    fun getAllCompletedTasks(): List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)

    @Delete
    fun deleteTask(task: Task)
}
