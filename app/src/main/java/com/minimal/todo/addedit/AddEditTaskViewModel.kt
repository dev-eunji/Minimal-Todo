package com.minimal.todo.addedit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.minimal.todo.data.Task
import com.minimal.todo.data.TasksRepository

class AddEditTaskViewModel : ViewModel() {

    private val tasksRepository: TasksRepository? = null

    val title = MutableLiveData<String>()       // Two-way databinding
    val description = MutableLiveData<String>() // Two-way databinding

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading


    private val _toastText = MutableLiveData<String>()
    val toastText: LiveData<String>
        get() = _toastText

    private var isNewTask: Boolean = false
    private var taskId: String? = null

    private var isDataLoaded = false    // 데이터 로드 완료되었는지
    private var taskCompleted = false   // 완료된 일인지

    fun init(taskId: String?) {

        _dataLoading.value?.let { isLoading ->
            // Already loading, ignore.
            if (isLoading) {
                return
            }
        }

        this.taskId = taskId
        if (taskId == null) {
            isNewTask = true
            return
        }

        if (isDataLoaded) {
            return
        }

        isNewTask = false
        _dataLoading.value = true

//        tasksRepository.getTask(taskId, this)
    }


    internal fun saveTask() {
        val currentTitle = title.value
        val currentDescription = description.value

        if (currentTitle == null || currentDescription == null) {
            _toastText.value = "TO DOs cannot be empty"
            return
        }
        if (Task(currentTitle, currentDescription).isEmpty) {
            _toastText.value = "TO DOs cannot be empty"
            return
        }

        val currentTaskId = taskId
        if (isNewTask || currentTaskId == null) {
            createNewTask(
                Task(
                    currentTitle,
                    currentDescription
                )
            )
        } else {
            Task(
                currentTitle,
                currentDescription,
                currentTaskId
            )
                .apply {
                    isCompleted = taskCompleted
                    updateTask(this)
                }
        }
    }

    /**
     * 새로운 Task 생성
     */
    private fun createNewTask(task: Task) {

    }

    /**
     * Task 업데이
     */
    private fun updateTask(task: Task) {

    }
}