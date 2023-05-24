package com.example.tasker.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Category::class, Task::class],
    version = 1,
)
abstract class MyDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        private var instance: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): MyDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                MyDatabase::class.java,
                "database",
            )
                .allowMainThreadQueries()
                .build()
        }
    }
}
