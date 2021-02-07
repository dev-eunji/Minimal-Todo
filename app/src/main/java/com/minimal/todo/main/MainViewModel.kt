package com.minimal.todo.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.minimal.todo.data.Task
import com.minimal.todo.data.TasksRepository

class MainViewModel : ViewModel() {

    private val tasksRepository: TasksRepository? = null

    private val _items = MutableLiveData<List<Task>>().apply { value = emptyList() }
    val items: LiveData<List<Task>>
        get() = _items

    private val _openTaskEvent = MutableLiveData<String>()
    val openTaskEvent: LiveData<String>
        get() = _openTaskEvent

    private val _newTaskEvent = MutableLiveData<Unit>()
    val newTaskEvent: LiveData<Unit>
        get() = _newTaskEvent

    private val _toastText = MutableLiveData<String>()
    val toastText: LiveData<String>
        get() = _toastText


    fun completeTask(task: Task, completed: Boolean) {
        if (completed) {
            _toastText.value = "DONE"
        } else {
            _toastText.value = "NOT DONE"
        }
    }

    internal fun openTask(taskId: String) {
        _openTaskEvent.value = taskId
    }

    fun addNewTask() {
        _newTaskEvent.value = Unit
    }
}