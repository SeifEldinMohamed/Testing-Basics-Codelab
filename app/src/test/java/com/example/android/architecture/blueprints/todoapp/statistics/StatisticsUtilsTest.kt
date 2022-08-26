package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import com.google.common.truth.Truth.assertThat

import org.junit.Test

class StatisticsUtilsTest {

    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {
        val tasksList: ArrayList<Task> = ArrayList<Task>()
        tasksList.add(
            Task(
                "task 1",
                "task 1 description",
                false,
                "1"
            )
        )

        val statsResult = getActiveAndCompletedStats(tasksList)
        assertThat(statsResult).isEqualTo(StatsResult(100f, 0f))
    }


@Test
fun getActiveAndCompletedStats_allCompleted_returnsZeroHundred() {
    val tasksList: ArrayList<Task> = ArrayList<Task>()
    tasksList.add(
        Task(
            "task 1",
            "task 1 description",
            true,
            "1"
        )
    )

    val statsResult = getActiveAndCompletedStats(tasksList)
    assertThat(statsResult).isEqualTo(StatsResult(0f, 100f))
}

    @Test
    fun getActiveAndCompletedStats_HalfCompletedAndHalfActive_returnsFiftyFifty() {
        val tasksList: ArrayList<Task> = ArrayList<Task>()
        tasksList.add(
            Task(
                "task 1",
                "task 1 description",
                true,
                "1"
            )
        )
        tasksList.add(
            Task(
                "task 2",
                "task 2 description",
                false,
                "2"
            )
        )
        val statsResult = getActiveAndCompletedStats(tasksList)
        assertThat(statsResult).isEqualTo(StatsResult(50f, 50f))
    }
    @Test
    fun getActiveAndCompletedStats_emptyList_returnsZeros() {
        val tasksList: List<Task> = emptyList()

        val statsResult = getActiveAndCompletedStats(tasksList)
        assertThat(statsResult).isEqualTo(StatsResult(0f, 0f))
    }
    @Test
    fun getActiveAndCompletedStats_error_returnsZeros() {
        val tasksList: ArrayList<Task>? = null

        val statsResult = getActiveAndCompletedStats(tasksList)
        assertThat(statsResult).isEqualTo(StatsResult(0f, 0f))
    }

}



/**
 * There are a few other strategies you can use for writing readable tests. Both of which are demonstrated in the test you just wrote.

Given, When, Then

One way to think about the structure of a test is to follow the Given, When, Then testing mnemonic. This divides your test into three parts:

 * Given: Setup the objects and the state of the app that you need for your test. For this test, what is "given" is that you have a list of tasks where the task is active.
When: Do the actual action on the object you're testing. For this test, it means calling getActiveAndCompletedStats.
Then: This is where you actually check what happens when you do the action where you check if the test passed or failed. This is usually a number of assert function calls. For this test, it is the two asserts that check you have the correct active and completed percentages.
Note that the" Arrange, Act, Assert" (AAA) testing mnemonic is a similar concept.

Test Names

T* est names are meant to be descriptive. The naming convention in this codelab is:

subjectUnderTest_actionOrInput_resultState

 * Subject under test is the method or class that is being tested (getActiveAndCompletedStats).
Next is the action or input (noCompleted).

Finally you have the expected result (returnsHundredZero).
 **/
