package com.example.tasker.data

import com.example.tasker.data.room.Category
import com.example.tasker.data.room.CategoryDao
import com.example.tasker.data.room.Task
import com.example.tasker.data.room.TaskDao

class Repository(
    private val taskDao: TaskDao,
    private val categoryDao: CategoryDao,
) {
    fun getAllCategories(): List<Category> {
        return categoryDao.getAllCategories()
    }

    fun addCategory(category: Category) {
        categoryDao.insertCategory(category)
    }

    fun deleteCategory(category: Category) {
        categoryDao.deleteCategory(category)
    }

    fun getNotCompletedTasksFromCategory(categoryName: String): List<Task> {
        return taskDao.getNotCompletedTasksFromCategory(categoryName)
    }

    fun getAllCompletedTasks(): List<Task> {
        return taskDao.getAllCompletedTasks()
    }

    fun addTask(task: Task) {
        taskDao.insertTask(task)
    }

    fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }
}
