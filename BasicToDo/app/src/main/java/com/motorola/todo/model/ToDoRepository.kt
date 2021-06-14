package com.motorola.todo.model

import androidx.lifecycle.LiveData

class ToDoRepository(private val todoDao: ToDoDao) {
    val readAllData: LiveData<List<ToDoItem>> = todoDao.getAll()

    suspend fun addToDo(todo: ToDoItem) {
        todoDao.insert(todo)
    }

    fun deleteToDo(todo: ToDoItem) {
        todoDao.delete(todo)
    }
}
