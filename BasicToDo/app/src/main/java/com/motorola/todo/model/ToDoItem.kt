package com.motorola.todo.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "ToDoItems")
@Parcelize
data class ToDoItem (
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String = "",
    @ColumnInfo(name = "createdDate") val createdDate: String = "None",
    @ColumnInfo(name = "first_name") var isDone: Boolean = false,
    @PrimaryKey(autoGenerate = true) val uid: Int = 0) : Parcelable
