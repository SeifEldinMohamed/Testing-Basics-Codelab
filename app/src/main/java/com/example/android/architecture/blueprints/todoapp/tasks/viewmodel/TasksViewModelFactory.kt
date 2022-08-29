package com.example.android.architecture.blueprints.todoapp.tasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository

@Suppress("UNCHECKED_CAST")
class TasksViewModelFactory(private val tasksRepository: TasksRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TasksViewModel(tasksRepository) as T
    }
}