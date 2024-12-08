package com.turk.medapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher

@ExperimentalCoroutinesApi
class MainDispatcherRule(
    val dispatcher: TestDispatcher = StandardTestDispatcher() // Use StandardTestDispatcher
) : TestWatcher() {

    // Set the main dispatcher to StandardTestDispatcher before each test
    override fun starting(description: Description?) {
        Dispatchers.setMain(dispatcher)
    }

    // Reset the main dispatcher to its original state after each test
    override fun finished(description: Description?) {
        Dispatchers.resetMain()
    }
}