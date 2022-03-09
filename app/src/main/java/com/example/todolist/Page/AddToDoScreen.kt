package com.example.todolist.Page

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todolist.Model.TodoItem
import com.example.todolist.Model.TodoViewModel
import com.example.todolist.Model.TodoViewModelFactory
import com.example.todolist.ui.components.fab.FABComponent
import com.example.todolist.ui.components.textfield.InputFieldComponent
import com.example.todolist.ui.gradbackground

@ExperimentalMaterialApi
@Composable
fun AddToDoScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .background(gradbackground)
            .fillMaxSize()
            .padding(start = 5.dp, end = 5.dp, top = 5.dp)

    ) {
        GreetingSectionAdd()
        AddTodoView(navController = navController)
    }
}

@ExperimentalMaterialApi
@Composable
fun GreetingSectionAdd() {
    val context = LocalContext.current
    Column(
        modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
    ) {
        Text(
            text = "Add List",
            style = MaterialTheme.typography.h4
        )
    }
}

@Composable
fun AddTodoView(navController: NavController) {
    val inputViewModel = InputViewModel()
    val context = LocalContext.current
    val mTodoViewModel: TodoViewModel = viewModel(
        factory = TodoViewModelFactory(context.applicationContext as Application)
    )

    Scaffold(
        floatingActionButton = {
            FABComponent(text = "Add Task", onClick = {
                insertTodoInDB(inputViewModel.todo.value.toString(), mTodoViewModel)

                Toast.makeText(context, "Added Task", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            })
        }
    ) {
        InputFieldState(inputViewModel)
    }
}

@Composable
private fun InputFieldState(inputViewModel: InputViewModel) {
    val todo: String by inputViewModel.todo.observeAsState("")

    Column(modifier = Modifier.padding(16.dp)) {
        InputField(todo) { inputViewModel.onInputChange(it) }
        Spacer(modifier = Modifier.padding(10.dp))
    }
}

@Composable
private fun InputField(
    name: String,
    onValChange: ((String) -> Unit)?
) {
    val focusManager = LocalFocusManager.current

    if (onValChange != null) {
        InputFieldComponent(
            text = name,
            onChange = onValChange,
            label = "Input Task",
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth()
                .testTag("TEST_INPUT_TAG"),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        )
    }
}

private fun insertTodoInDB(todo: String, mTodoViewModel: TodoViewModel) {
    if (todo.isNotEmpty()) {
        val todoItem = TodoItem(
            itemName = todo,
            isDone = false
        )

        mTodoViewModel.addTodo(todoItem)
    }
}

class InputViewModel : ViewModel() {
    private val _todo: MutableLiveData<String> = MutableLiveData("")
    val todo: LiveData<String> = _todo

    fun onInputChange(newName: String) {
        _todo.value = newName
    }
}
