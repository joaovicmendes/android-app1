package com.motorola.todo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab: View = findViewById(R.id.fab_add_todo)
        fab.setOnClickListener { view ->
            openNewToDoActivity(view)
        }
    }

    private fun openNewToDoActivity(view: View) {
        val intent = Intent(this, NewToDoActivity::class.java)
        startActivity(intent)
    }
}