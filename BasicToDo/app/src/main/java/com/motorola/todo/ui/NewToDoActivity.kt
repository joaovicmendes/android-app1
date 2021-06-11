package com.motorola.todo.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.motorola.todo.R
import com.motorola.todo.model.ToDoItem
import kotlinx.android.synthetic.main.activity_new_to_do.*

class NewToDoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_to_do)

        val fab: View = findViewById(R.id.fab_save_todo)
        fab.setOnClickListener {
            val title = ed_add_todo_title.text.toString()
            val description = ed_add_todo_description.text.toString()

            if (title.isEmpty()) {
                setResult(RESULT_CANCELED, intent)
                finish()
            }

            val todo = ToDoItem(title, description)
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("NEW_TODO", todo)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}