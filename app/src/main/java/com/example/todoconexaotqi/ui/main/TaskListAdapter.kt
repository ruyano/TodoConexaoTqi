package com.example.todoconexaotqi.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.todoconexaotqi.BR
import com.example.todoconexaotqi.R
import com.example.todoconexaotqi.model.Task

class TaskListAdapter(val viewModel: MainViewModel, val tasks: MutableList<Task> = mutableListOf()): RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(viewModel, position)
    }

    override fun getItemViewType(position: Int) = R.layout.task_list_item

    fun updateList(tasksList: List<Task>) {
        tasks.clear()
        tasks.addAll(tasksList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {

        fun bind(viewModel: MainViewModel, position: Int?) {
            viewDataBinding.setVariable(BR.model, viewModel)
            viewDataBinding.setVariable(BR.position, position)
            viewDataBinding.executePendingBindings()
        }
    }

}