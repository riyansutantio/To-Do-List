package com.example.todolist.Util

sealed class Screen(val route: String){
    object Welcomescreen : Screen("Welcome_Screen")
    object AddToDoscreen : Screen("Add_ToDo")
    object EditToDoscreen : Screen("Edit_ToDo")

}
