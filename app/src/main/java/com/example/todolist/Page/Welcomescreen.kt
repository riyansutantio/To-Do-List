package com.example.todolist.Page

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aseemwangoo.handsonkotlin.ui.components.button.SimpleButtonComponent
import com.example.todolist.Model.TodoItem
import com.example.todolist.Model.TodoViewModel
import com.example.todolist.Model.TodoViewModelFactory
import com.example.todolist.Util.Screen
import com.example.todolist.ui.*


@ExperimentalMaterialApi
@Composable
fun WelcomeScreen(navController: NavController) {
    val context = LocalContext.current
    val mTodoViewModel: TodoViewModel = viewModel(
        factory = TodoViewModelFactory(context.applicationContext as Application)
    )
    val items = mTodoViewModel.readAllData.observeAsState(listOf()).value
    Box(
        modifier = Modifier
            .background(gradbackground)
            .fillMaxSize()
            .padding(start = 5.dp, end = 5.dp, top = 5.dp)

    ) {
        Column() {
            GreetingSection()
            CustomCardState(navController, mTodoViewModel)
            Isi(list = items, mTodoViewModel = mTodoViewModel)
        }
    }
}
@ExperimentalMaterialApi
@Composable
fun GreetingSection() {
    Column(
        modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome To TODOLIST",
            style = MaterialTheme.typography.h4
        )
    }
}

@Composable
private fun CustomCardState(
    navController: NavController,
    mTodoViewModel: TodoViewModel
) {
    val context = LocalContext.current
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            SimpleButtonComponent(text = "ADD Task", onClick = {
                navController.navigate(Screen.AddToDoscreen.route)
            })
            SimpleButtonComponent(text = "Clear All Task", onClick = {
                mTodoViewModel.deleteAllTodos()
                Toast.makeText(context, "All Task are Deleted", Toast.LENGTH_SHORT).show()
            })
        }
    }
}
@ExperimentalMaterialApi
@Composable
fun Isi(
    list: List<TodoItem>,
    mTodoViewModel: TodoViewModel
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .fillMaxSize()
            .padding(15.dp)
            .border(BorderStroke(2.dp, color = LightGreen1))
    ) {
        val context = LocalContext.current

        LazyColumn() {
            items(list){ todo ->
                val name = rememberSaveable { mutableStateOf(todo.isDone) }

                ListItem(
                    text = { Text(text = todo.itemName) },
                    icon = {
                        IconButton(onClick = {
                            mTodoViewModel.deleteTodo(todo)
                            Toast.makeText(context, "Task '${todo.itemName}' is Deleted", Toast.LENGTH_SHORT).show()
                        }) {
                            Column {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = null
                                )
                                Text(text = "Delete")
                            }
                        }
                    },
                    trailing = {
                        Checkbox(
                            checked = name.value,
                            onCheckedChange = {
                                name.value = it
                                todo.isDone = name.value
                                mTodoViewModel.updateTodo(todo)

                                Toast.makeText(context, "Task '${todo.itemName}' is Done", Toast.LENGTH_SHORT).show()
                            },
                        )
                    }
                )
                Divider()
            }
        }
    }
}