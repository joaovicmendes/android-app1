package com.motorola.todo.model

import androidx.lifecycle.LiveData

class ToDoRepository(private val todoDao: ToDoDao) {
    val readAllData: LiveData<List<ToDoItem>> = todoDao.getAll()

    suspend fun insert(todo: ToDoItem) {
        todoDao.insert(todo)
    }

    suspend fun update(todo: ToDoItem) {
        todoDao.update(todo)
    }

    fun delete(todo: ToDoItem) {
        todoDao.delete(todo)
    }
}
