package com.minimal.todo.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.minimal.todo.data.Task
import com.minimal.todo.databinding.ItemTaskBinding

class MainAdapter(
    private var tasks: List<Task>,
    private var viewModel: MainViewModel
) : RecyclerView.Adapter<MainAdapter.TaskViewHolder>() {

    fun replaceData(tasks: List<Task>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = tasks.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemTaskBinding =
            ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(itemTaskBinding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            val userActionListener = object : UserActionListener {
                override fun onCompleteChanged(task: Task, v: View) {
                    val checked = (v as CheckBox).isChecked
                    viewModel.completeTask(task, checked)
                }

                override fun onTaskClicked(task: Task) {
                    viewModel.openTask(task.id)
                }
            }
            binding.task = task
            binding.listener = userActionListener
        }
    }

    interface UserActionListener {
        fun onCompleteChanged(task: Task, v: View)

        fun onTaskClicked(task: Task)
    }
}