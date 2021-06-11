package com.motorola.todo.adapter

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.motorola.todo.R
import com.motorola.todo.model.ToDoItem
import kotlinx.android.synthetic.main.todo_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class ToDoAdapter(
    private val todos: MutableList<ToDoItem>
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.todo_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val currToDo = todos[position]
        holder.itemView.apply {
            // Binding To Do data to adequate ViewHolder
            tv_todo_title.text = currToDo.title
            tv_todo_description.text = currToDo.description
            tv_todo_date.text = SimpleDateFormat("dd/MM/yyyy").format(
                currToDo.createdDate.time
            )
            cb_todo_done.isChecked = currToDo.isDone

            // If is marked as done, add strike through
            toggleStrikeThrough(tv_todo_title, currToDo.isDone)

            // Add event to update strike trough if done status changes
            cb_todo_done.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tv_todo_title, isChecked)
                currToDo.isDone = !currToDo.isDone
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    private fun toggleStrikeThrough(tvToDoTitle: TextView, isDone: Boolean) {
        if (isDone) {
            tvToDoTitle.paintFlags = tvToDoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvToDoTitle.paintFlags = tvToDoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    fun addToDo(todo: ToDoItem) {
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }
}
