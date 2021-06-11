package com.motorola.todo.model

import java.util.*

data class ToDoItem (
    val title: String,
    val description: String = "",
    val createdDate: Calendar = Calendar.getInstance(),
    var isDone: Boolean = false)
