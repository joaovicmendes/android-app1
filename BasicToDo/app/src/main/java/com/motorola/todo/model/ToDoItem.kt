package com.motorola.todo.model

import java.util.*

data class ToDoItem (
    var title: String,
    var description: String = "",
    var createdDate: Calendar = Calendar.getInstance(),
    val isDone: Boolean = false)
