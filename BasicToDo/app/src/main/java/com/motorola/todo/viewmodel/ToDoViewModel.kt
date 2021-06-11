package com.motorola.todo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.motorola.todo.model.ToDoItem

class ToDoViewModel : ViewModel() {
    private val todos: MutableLiveData<MutableList<ToDoItem>> = MutableLiveData<MutableList<ToDoItem>>()

    fun getToDos(): LiveData<MutableList<ToDoItem>> {
        return this.todos
    }
}
