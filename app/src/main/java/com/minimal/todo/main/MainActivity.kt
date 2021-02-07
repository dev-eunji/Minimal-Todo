package com.minimal.todo.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.minimal.todo.R
import com.minimal.todo.addedit.AddEditTaskActivity
import com.minimal.todo.data.Task
import com.minimal.todo.databinding.ActivityMainBinding
import com.minimal.todo.taskdetail.TaskDetailActivity

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        ).apply {
                lifecycleOwner = this@MainActivity
                viewmodel = viewModel
                recyclerView.apply {
                    setHasFixedSize(true)
                    adapter = MainAdapter(
                        mutableListOf(
                            Task("title1", "desc1"),
                            Task("title2", "desc2")
                        ), viewModel
                    )
                }
            }

        viewModel.run {
            openTaskEvent.observe(this@MainActivity, Observer { taskId ->
                Intent(this@MainActivity, TaskDetailActivity::class.java).apply {
                    putExtra( "TASK_ID", taskId)
                    startActivity(this)
                }
            })

            newTaskEvent.observe(this@MainActivity, Observer {
                Intent(this@MainActivity, AddEditTaskActivity::class.java).apply {
                    startActivity(this)
                }
            })

            toastText.observe(this@MainActivity, Observer {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            })
        }
    }
}
