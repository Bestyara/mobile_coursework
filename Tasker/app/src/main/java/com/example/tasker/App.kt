package com.example.tasker

import android.app.Application
import com.example.tasker.data.Repository
import com.example.tasker.data.room.MyDatabase

class App : Application() {
    lateinit var database: MyDatabase
    lateinit var repository: Repository

    override fun onCreate() {
        super.onCreate()
        database = MyDatabase.getInstance(this)
        val taskDao = database.taskDao()
        val categoryDao = database.categoryDao()
        repository = Repository(taskDao, categoryDao)
    }
}
