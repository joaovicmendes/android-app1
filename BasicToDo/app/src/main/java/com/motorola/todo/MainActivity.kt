package com.motorola.todo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import com.motorola.todo.adapter.ToDoAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: ToDoAdapter

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
        startActivity(intent)
    }
}
