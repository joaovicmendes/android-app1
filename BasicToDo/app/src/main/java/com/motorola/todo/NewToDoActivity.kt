package com.motorola.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_new_to_do.*
import com.motorola.todo.model.ToDoItem

class NewToDoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_to_do)

        val fab: View = findViewById(R.id.fab_save_todo)
        fab.setOnClickListener {
            val title = tv_add_todo_title.text.toString()
            val description = tv_add_todo_description.text.toString()
            if (title.isEmpty()) {
                TODO("Aviso de erro")
            }

            val todo = ToDoItem(title, description)
            TODO("Salvar no item")
        }
    }
}