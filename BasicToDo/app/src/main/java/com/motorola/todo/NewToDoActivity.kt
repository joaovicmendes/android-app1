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