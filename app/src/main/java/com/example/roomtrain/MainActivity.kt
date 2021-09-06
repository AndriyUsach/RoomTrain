package com.example.roomtrain

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.example.roomtrain.data.Employee
import com.example.roomtrain.data.EmployeeDao
import com.example.roomtrain.data.EmployeeStore
import com.example.roomtrain.ui.MainScreen
import com.example.roomtrain.ui.theme.RoomTrainTheme

class MainActivity : ComponentActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        val employeeDao = App.instance.database.employeeDao()
        val employeeStore = EmployeeStore(employeeDao)
        mainViewModel = MainViewModel(employeeStore)
        mainViewModel.getAll()

        super.onCreate(savedInstanceState)
        setContent {
            RoomTrainTheme {
                mainViewModel.employeeViewModel.observeAsState().value?.let { employeeList ->
                    MainScreen(
                        employeeList = employeeList,
                        create = { id, name, salary -> mainViewModel.create(id, name, salary) },
                        update = { id, name, salary -> mainViewModel.update(id, name, salary) },
                        delete = { id -> mainViewModel.delete(id) },
                        getAll = { mainViewModel.getAll() },
                        getById = { id -> mainViewModel.getById(id) }
                    )
                }
            }
        }
    }
}
