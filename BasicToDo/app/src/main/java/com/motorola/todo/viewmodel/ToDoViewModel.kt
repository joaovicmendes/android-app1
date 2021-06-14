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

    fun getAll(): LiveData<List<ToDoItem>> {
        return this.todos
    }

    fun add(todo: ToDoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(todo)
        }
    }

    fun toggleDone(todo: ToDoItem) {
        todo.isDone = !todo.isDone
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(todo)
        }
    }

    fun delete(todo: ToDoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(todo)
        }
    }
}
