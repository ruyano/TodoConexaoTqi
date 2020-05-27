package com.example.todoconexaotqi.ui.add

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.todoconexaotqi.R
import com.example.todoconexaotqi.databinding.ActivityAddTaskBinding
import kotlinx.android.synthetic.main.activity_add_task.*

class AddTaskActivity : AppCompatActivity() {

    lateinit var viewModel: AddTaskViewModel
    lateinit var dataBinding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_task)
        viewModel = ViewModelProviders.of(this).get(AddTaskViewModel::class.java)
        dataBinding.model = viewModel


        add_button.setOnClickListener {
            viewModel.addTask()
        }

        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.shouldCloseActivity.observe(this, Observer {
            if (it) {
                finish()
            }
        })
    }
}
