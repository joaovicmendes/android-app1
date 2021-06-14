package com.motorola.todo.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.motorola.todo.R
import com.motorola.todo.model.ToDoItem
import kotlinx.android.synthetic.main.activity_new_to_do.*
import java.text.SimpleDateFormat
import java.util.*

class NewToDoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_to_do)

        val fab: View = findViewById(R.id.fab_save_todo)
        fab.setOnClickListener {
            val title = ed_add_todo_title.text.toString()
            val description = ed_add_todo_description.text.toString()

            if (title.isNotEmpty()) {
                val createdDate = SimpleDateFormat("dd/MM/yyyy").format(
                    Calendar.getInstance().time
                )
                val todo = ToDoItem(title, description, createdDate)
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("NEW_TODO", todo)
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(
                    applicationContext,
                    R.string.todo_empty_title,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}