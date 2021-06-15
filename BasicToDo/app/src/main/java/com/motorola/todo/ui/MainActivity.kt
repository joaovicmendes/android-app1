package com.motorola.todo.ui

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.motorola.todo.R
import com.motorola.todo.adapter.ToDoAdapter
import com.motorola.todo.model.ToDoItem
import com.motorola.todo.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ToDoAdapter.ToDoClickListener {
    private lateinit var todoAdapter: ToDoAdapter
    private lateinit var todoViewModel: ToDoViewModel
    private val addNewTodoRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Binding FAB to create new To Do
        val fab: View = findViewById(R.id.fab_add_todo)
        fab.setOnClickListener {
            openNewToDoActivity()
        }

        // Different application Views
        val todoList: View = findViewById(R.id.rv_todo_items)
        val emptyList: View = findViewById(R.id.tv_todo_items_empty)
        val progressBar: View = findViewById(R.id.pb_todo_items)

        // Initial View configuration
        todoList.visibility    = View.INVISIBLE
        emptyList.visibility   = View.INVISIBLE
        progressBar.visibility = View.VISIBLE

        todoAdapter = ToDoAdapter(this)
        rv_todo_items.adapter = todoAdapter
        rv_todo_items.layoutManager = LinearLayoutManager(this)

        // Callback to update RecycleView items
        todoViewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)
        todoViewModel.todos.observe(this, { todos ->
            progressBar.visibility = View.VISIBLE
            todoAdapter.setList(todos)
            progressBar.visibility = View.INVISIBLE

            if (todoAdapter.itemCount == 0) {
                todoList.visibility = View.INVISIBLE
                emptyList.visibility = View.VISIBLE
            } else {
                todoList.visibility = View.VISIBLE
                emptyList.visibility = View.INVISIBLE
            }
        })
    }

    // Create intent to open add To Do activity
    private fun openNewToDoActivity() {
        val intent = Intent(this, NewToDoActivity::class.java)
        startActivityForResult(intent, addNewTodoRequestCode)
    }

    // Handle return from the add To Do activity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == addNewTodoRequestCode && resultCode == RESULT_OK) {
            if (data != null) {
                data.getParcelableExtra<ToDoItem>("NEW_TODO")?.let {
                    // Add new ToDoItem to ViewModel
                    todoViewModel.add(it)

                    // Show success message
                    Toast.makeText(
                        this,
                        R.string.todo_added_success,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    /* Implemented functions from ToDoAdapter.ToDoClickListener interface: */
    // Prompt menu to delete ToDoItem when LongPressing
    override fun onLongClick(todo: ToDoItem) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.todo_delete_title)
            .setMessage(R.string.todo_delete_ask)
            .setPositiveButton(R.string.todo_delete_confirm,
                DialogInterface.OnClickListener { dialog, _ ->
                    todoViewModel.delete(todo)
                    dialog.dismiss()
                    Toast.makeText(
                        this,
                        R.string.todo_delete_success,
                        Toast.LENGTH_LONG
                    ).show()
                })
            .setNegativeButton(R.string.todo_delete_cancel,
                DialogInterface.OnClickListener { dialog, _ ->
                    dialog.cancel()
                })

        builder.create()
        builder.show()
    }

    // Mark ToDoItem as Done
    override fun onClick(todo: ToDoItem) {
        todoViewModel.toggleDone(todo)
    }

    // Mark ToDoItem as Done
    override fun onCheckClick(todo: ToDoItem) {
        todoViewModel.toggleDone(todo)
    }
}