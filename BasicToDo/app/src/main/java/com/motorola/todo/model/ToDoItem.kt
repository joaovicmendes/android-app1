package com.motorola.todo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class ToDoItem (
    val title: String,
    val description: String = "",
    val createdDate: Calendar = Calendar.getInstance(),
    var isDone: Boolean = false) : Parcelable
