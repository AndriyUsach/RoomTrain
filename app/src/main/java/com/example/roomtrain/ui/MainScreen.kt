package com.example.roomtrain.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomtrain.data.Employee

@Composable
fun MainScreen(
    employeeList: List<Employee>,
    create: (Int, String, Int) -> Unit,
    update: (Int, String, Int) -> Unit,
    delete: (Int) -> Unit,
    getAll: () -> Unit,
    getById: (Int) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 8.dp)) {
        LazyColumn {
            item {
                CreateEmployee(create = create)
                Spacer(modifier = Modifier.height(10.dp))
                UpdateEmployee(update = update)
                Spacer(modifier = Modifier.height(10.dp))
                DeleteEmployee(delete = delete)
                Spacer(modifier = Modifier.height(10.dp))
                GetEmployee(
                    getAll = getAll,
                    getById = getById
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Employee List",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            items(employeeList) { item ->
                EmployeeCard(employee = item)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun EmployeeCard(employee: Employee) {
    Column(modifier = Modifier.border(1.dp, Color.Gray)) {
        Text(text = "#${employee.id}  ${employee.name}\nSalary: ${employee.salary}")
    }
}

@Composable
fun CreateEmployee(
    create: (Int, String, Int) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val idText = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val nameText = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val salaryText = remember {
        mutableStateOf(TextFieldValue(""))
    }


    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Create", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,
            fontSize = 24.sp)
        Spacer(modifier = Modifier.height(8.dp))
       // id field
        TextField(
            value = idText.value,
            onValueChange = {
                idText.value = it
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }){
            },
            label = {
                Text(text = "ID")
            },
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(8.dp))
        
        TextField(
            value = nameText.value,
            onValueChange = { nameText.value = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions ( onDone = { focusManager.clearFocus() }),
            singleLine = true,
            label = { 
                Text(text = "Name")
            }
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        TextField(
            value = salaryText.value,
            onValueChange = { salaryText.value = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions( onDone = { focusManager.clearFocus() } ),
            singleLine = true,
            label = {  
                Text(text = "Salary")
            }
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Button(onClick = {
            create(
                idText.value.text.toInt(),
                nameText.value.text,
                salaryText.value.text.toInt()
            )
        }) {
            Text(text = "Create")
        }
    }
}

//@Preview("Create")
//@Composable
//fun CreateEmployeePreview() {
//    RoomTrainTheme() {
//        CreateEmployee()
//    }
//}


@Composable
fun GetEmployee(
    getAll: () -> Unit,
    getById: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {

        val focusManager = LocalFocusManager.current

        val idText = remember {
            mutableStateOf(TextFieldValue(""))
        }

        Text(
            text = "GET ALL OR GET BY ID", modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = idText.value,
            onValueChange = { idText.value = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions( onDone = { focusManager.clearFocus() }),
            singleLine = true,
            label = {
                Text(text = "ID")
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start) {
            Button(onClick = {
                getById(idText.value.text.toInt())
            },) {
                Text(text = "GetByID")
            }
            Spacer(modifier = Modifier.width(50.dp))
            Button(onClick = { getAll() }) {
                Text(text = "GET ALL")
            }
        }
    }
}

//@Preview("GET By ID Or GET ALL")
//@Composable
//fun GetEmployeePreview() {
//    RoomTrainTheme {
//        GetEmployee()
//    }
//}

@Composable
fun DeleteEmployee(
    delete: (Int) -> Unit
) {

    val focusManager = LocalFocusManager.current

    val idText = remember {
        mutableStateOf(TextFieldValue(""))
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Delete",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = idText.value,
            onValueChange = { idText.value = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            singleLine = true,
            label = {
                Text(text = "ID")
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            delete(idText.value.text.toInt())
        }) {
            Text(text = "Delete")
        }
    }
}

//@Preview("DeleteEmployee")
//@Composable
//fun DeleteEmployeePreview() {
//    RoomTrainTheme {
//        DeleteEmployee()
//    }
//}

@Composable
fun UpdateEmployee(
    update: (Int, String, Int) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val idText = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val nameText = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val salaryText = remember {
        mutableStateOf(TextFieldValue(""))
    }


    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Update", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,
            fontSize = 24.sp)
        Spacer(modifier = Modifier.height(8.dp))
        // id field
        TextField(
            value = idText.value,
            onValueChange = {
                idText.value = it
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }){
            },
            label = {
                Text(text = "ID")
            },
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = nameText.value,
            onValueChange = { nameText.value = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions ( onDone = { focusManager.clearFocus() }),
            singleLine = true,
            label = {
                Text(text = "Name")
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = salaryText.value,
            onValueChange = { salaryText.value = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions( onDone = { focusManager.clearFocus() } ),
            singleLine = true,
            label = {
                Text(text = "Salary")
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            update(
                idText.value.text.toInt(),
                nameText.value.text,
                salaryText.value.text.toInt()
            )
        }) {
            Text(text = "Update")
        }
    }
}

//@Preview("UpdateEmployee")
//@Composable
//fun UpdateEmployeePreview() {
//    RoomTrainTheme {
//        UpdateEmployee()
//    }
//}
