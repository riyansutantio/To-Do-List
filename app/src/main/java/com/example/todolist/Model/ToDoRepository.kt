package com.example.todolist.Model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoRepository(private val todoDatabaseDao: TodoDatabaseDao) {

    val readAllData: LiveData<List<TodoItem>> = todoDatabaseDao.getAll()

    suspend fun addTodo(todoItem: TodoItem) {
        todoDatabaseDao.insert(todoItem)
    }

    suspend fun updateTodo(todoItem: TodoItem) {
        todoDatabaseDao.update(todoItem)
    }

    suspend fun deleteTodo(todoItem: TodoItem) {
        todoDatabaseDao.delete(todoItem)
    }

    suspend fun deleteAllTodos() {
        todoDatabaseDao.deleteAllTodos()
    }
}