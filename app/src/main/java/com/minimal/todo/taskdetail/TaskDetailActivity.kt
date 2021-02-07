package com.minimal.todo.taskdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.minimal.todo.R
import com.minimal.todo.databinding.ActivityTaskDetailBinding

class TaskDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityTaskDetailBinding>(
                this,
                R.layout.activity_task_detail
            ).apply {
                lifecycleOwner = this@TaskDetailActivity
            }
    }
}