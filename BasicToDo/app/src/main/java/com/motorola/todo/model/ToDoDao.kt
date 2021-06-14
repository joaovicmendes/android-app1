package com.motorola.todo.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDao {
    @Query("SELECT * FROM ToDoItems")
    fun getAll(): LiveData<List<ToDoItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: ToDoItem)

    @Delete
    fun delete(todo: ToDoItem)
}
