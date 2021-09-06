package com.example.roomtrain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.roomtrain.data.Employee
import com.example.roomtrain.data.EmployeeStore

class MainViewModel(private val employeeStore: EmployeeStore) {

    private val _employeeViewModel = MutableLiveData<List<Employee>>()
    val employeeViewModel: LiveData<List<Employee>>
        get() = _employeeViewModel

    fun create(id: Int, name: String, salary: Int) {
        val newEmployee = Employee(
            id = id.toLong(),
            name = name,
            salary = salary
        )
        employeeStore.create(newEmployee)
    }

    fun update(id: Int, name: String, salary: Int) {
        val newEmployee = Employee(
            id = id.toLong(),
            name = name,
            salary = salary
        )
        employeeStore.update(newEmployee)
    }

    fun getAll() {
        val employeeList = employeeStore.getAll()
        _employeeViewModel.value = employeeList
    }

    fun getById(id: Int) {
        val employee = employeeStore.getById(id = id)
        _employeeViewModel.value = employee
    }

    fun delete(id: Int) {
        employeeStore.delete(id = id)
    }
}