package com.example.todolist.Util


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolist.Page.AddToDoScreen
import com.example.todolist.Page.EditToDoScreen
import com.example.todolist.Page.WelcomeScreen

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Welcomescreen.route){
        composable(route = Screen.Welcomescreen.route){
            WelcomeScreen(navController)
        }
        composable(route = Screen.AddToDoscreen.route){
            AddToDoScreen(navController)
        }
        composable(route = Screen.EditToDoscreen.route){
            EditToDoScreen(navController)
        }

    }
}