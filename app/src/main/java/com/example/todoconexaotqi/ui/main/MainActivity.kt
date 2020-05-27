package com.example.todoconexaotqi.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.todoconexaotqi.R
import com.example.todoconexaotqi.databinding.ActivityMainBinding
import com.example.todoconexaotqi.ui.add.AddTaskActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var adapter: TaskListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        dataBinding.model = viewModel
        adapter = TaskListAdapter(viewModel)
        rv_tasks.adapter = adapter

        viewModel.tasksList.observe(this, Observer { tasks ->
            viewModel.updateVisibility()
            adapter.updateList(tasks)
        })

        fab_add.setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java))
        }
    }
}
