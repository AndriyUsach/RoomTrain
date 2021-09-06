package com.example.roomtrain.data

class EmployeeStore(private val employeeDao: EmployeeDao) {

    fun getAll(): List<Employee> {
        return employeeDao.getAll()
    }

    fun getById(id: Int): List<Employee> {
        return listOf(employeeDao.getById(id.toLong()))
    }

    fun update(employee: Employee) {
        employeeDao.update(employee)
    }

    fun delete(id: Int) {
        val employee = employeeDao.getById(id.toLong())
        employeeDao.delete(employee)
    }

    fun create(employee: Employee) {
        employeeDao.insert(employee)
    }
}