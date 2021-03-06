package com.example.roomtrain.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Employee::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao
}