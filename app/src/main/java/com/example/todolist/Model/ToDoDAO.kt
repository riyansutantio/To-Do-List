package com.example.todolist.Model

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlin.collections.List

@Dao
interface TodoDatabaseDao {
    @Query("SELECT * from my_todo_list")
    fun getAll(): LiveData<List<TodoItem>>

    @Query("SELECT * from my_todo_list where itemId = :id")
    fun getById(id: Int): TodoItem?

    @Insert
    fun insert(item: TodoItem)

    @Update
    fun update(item: TodoItem)

    @Delete
    fun delete(item: TodoItem)

    @Query("DELETE FROM my_todo_list")
    fun deleteAllTodos()
}