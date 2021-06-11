package com.motorola.todo.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.motorola.todo.R
import com.motorola.todo.adapter.ToDoAdapter
import com.motorola.todo.model.ToDoItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: ToDoAdapter
    private val addNewTodoRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab: View = findViewById(R.id.fab_add_todo)
        fab.setOnClickListener { view ->
            openNewToDoActivity(view)
        }

        // @TODO pull data from DB
        todoAdapter = ToDoAdapter(mutableListOf())

        rvToDoItems.adapter = todoAdapter
        rvToDoItems.layoutManager = LinearLayoutManager(this)
    }

    private fun openNewToDoActivity(view: View) {
        val intent = Intent(this, NewToDoActivity::class.java)
        startActivityForResult(intent, addNewTodoRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == addNewTodoRequestCode && resultCode == RESULT_OK) {

            if (data != null) {
                data.getParcelableExtra<ToDoItem>("NEW_TODO")?.let {
                    todoAdapter.addToDo(it)
                }
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.todo_empty_title,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}