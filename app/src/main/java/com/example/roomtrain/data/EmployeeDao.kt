package com.example.roomtrain.data

import androidx.room.*


@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employees")
    fun getAll(): List<Employee>

    @Query("SELECT * FROM employees WHERE id = :id")
    fun getById(id: Long): Employee

    @Insert
    fun insert(employee: Employee)

    @Update
    fun update(employee: Employee)

    @Delete
    fun delete(employee: Employee)
}