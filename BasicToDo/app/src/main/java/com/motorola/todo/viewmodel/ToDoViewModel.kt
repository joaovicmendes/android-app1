package com.motorola.todo.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import com.motorola.todo.model.ToDoDatabase
import com.motorola.todo.model.ToDoItem
import com.motorola.todo.model.ToDoRepository
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application) : AndroidViewModel(application) {
    var todos: LiveData<List<ToDoItem>>
    private var repository: ToDoRepository

    init {
        val todoDao = ToDoDatabase.getDatabase(application).todoDao()
        repository = ToDoRepository(todoDao)
        todos = repository.readAllData
    }

    fun getAllToDo(): LiveData<List<ToDoItem>> {
        return this.todos
    }

    fun addToDo(todo: ToDoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addToDo(todo)
        }
    }
}
