package com.example.android.architecture.blueprints.todoapp.data.source

import androidx.lifecycle.LiveData
import com.example.android.architecture.blueprints.todoapp.data.Result
import com.example.android.architecture.blueprints.todoapp.data.Task
import java.lang.Exception

class FakeDataSource(var tasks: MutableList<Task>? = mutableListOf()): TasksDataSource {
    override fun observeTasks(): LiveData<Result<List<Task>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTasks(): Result<List<Task>> {
        tasks?.let {
            return Result.Success(it)
        }
        return Result.Error(Exception("tasks not found"))
    }

    override suspend fun refreshTasks() {
        TODO("Not yet implemented")
    }

    override fun observeTask(taskId: String): LiveData<Result<Task>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTask(taskId: String): Result<Task> {
        tasks?.let {
            return Result.Success(it[taskId.toInt()])
        }
        return Result.Error(Exception("not found"))
    }

    override suspend fun refreshTask(taskId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun saveTask(task: Task) {
        tasks?.add(task)
    }

    override suspend fun completeTask(task: Task) {
        tasks?.get(task.id.toInt())?.isCompleted = true
    }

    override suspend fun completeTask(taskId: String) {
        tasks?.get(taskId.toInt())?.isCompleted = true
    }

    override suspend fun activateTask(task: Task) {
        tasks?.get(task.id.toInt())?.isCompleted = false
    }

    override suspend fun activateTask(taskId: String) {
        tasks?.get(taskId.toInt())?.isCompleted = false
    }

    override suspend fun clearCompletedTasks() {
        tasks?.forEach {
            if(it.isCompleted)
                tasks?.remove(it)
        }
    }

    override suspend fun deleteAllTasks() {
        tasks?.clear()
    }

    override suspend fun deleteTask(taskId: String) {
        tasks?.removeAt(taskId.toInt())
    }
}