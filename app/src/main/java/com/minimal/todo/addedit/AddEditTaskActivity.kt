package com.minimal.todo.addedit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.minimal.todo.R
import com.minimal.todo.databinding.ActivityAddEditBinding

class AddEditTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditBinding
    private lateinit var viewModel: AddEditTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityAddEditBinding>(this, R.layout.activity_add_edit)
                .apply {
                    lifecycleOwner = this@AddEditTaskActivity
                }
        loadTask()
    }

    /**
     * 기존에 있는 작업이면 Edit, 없으면 Add 로 초기화
     *
     */
    private fun loadTask() {
        binding.viewmodel?.init(intent.getStringExtra(ARGUMENT_EDIT_TASK_ID))
    }

    companion object {
        const val ARGUMENT_EDIT_TASK_ID = "EDIT_TASK_ID"
    }
}