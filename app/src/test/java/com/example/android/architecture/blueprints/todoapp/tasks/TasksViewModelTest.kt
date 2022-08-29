package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.FakeTestRepository
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import com.example.android.architecture.blueprints.todoapp.tasks.viewmodel.TasksViewModel
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [30])
class TasksViewModelTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // Use a fake repository to be injected into the viewmodel
    private lateinit var tasksRepository: FakeTestRepository

    lateinit var tasksViewModel: TasksViewModel

    @Before
    fun setup(){

        // We initialise the tasks to 3, with one active and two completed
        tasksRepository = FakeTestRepository()
        val task1 = Task("Title1", "Description1")
        val task2 = Task("Title2", "Description2", true)
        val task3 = Task("Title3", "Description3", true)
        tasksRepository.addTasks(task1, task2, task3)
        // Given a fresh ViewModel
        tasksViewModel = TasksViewModel(tasksRepository)
    }

    @Test
    fun addNewTask_setsNewTaskEvent() {
        // When adding a new task
        tasksViewModel.addNewTask()
        val value = tasksViewModel.newTaskEvent.getOrAwaitValue()

        // Then the new task event is triggered
        assertThat(value.getContentIfNotHandled()).isNotNull()
    }
    @Test
    fun setFilterAllTasks_tasksAddViewVisible() {
        // When the filter type is ALL_TASKS
        tasksViewModel.setFiltering(TasksFilterType.ALL_TASKS)
        val value = tasksViewModel.tasksAddViewVisible.getOrAwaitValue()
        // Then the "Add task" action is visible
        assertThat(value).isTrue()
    }

}

/**
 * To test LiveData it's recommended you do two things:
1- Use InstantTaskExecutorRule
2- Ensure LiveData observation
 **/

/**
 * InstantTaskExecutorRule is a JUnit Rule. When you use it with the @get:Rule annotation,
 * it causes some code in the InstantTaskExecutorRule class to be run before and after the tests .

This rule runs all Architecture Components-related background jobs in the same thread
so that the test results happen synchronously, and in a repeatable order.
When you write tests that include testing LiveData, use this rule!
 * */

// getContentIfNotHandled provides the "one-time" capability. Called the first time,
// it gets the content of the Event. Any additional calls to getContentIfNotHandled for the
// same content will return null.