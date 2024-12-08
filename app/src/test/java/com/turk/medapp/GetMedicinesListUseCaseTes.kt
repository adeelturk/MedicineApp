package com.turk.medapp

import com.turk.medapp.business.GetMedicinesListUseCase
import com.turk.medapp.core.Either
import com.turk.medapp.core.Failure
import com.turk.medapp.data.dtos.Medicine
import com.turk.medapp.data.repository.MedicineRepository
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetMedicinesListUseCaseTes {

    private val repo: MedicineRepository = mockk()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @After
    fun tearDown() {
        unmockkAll()
        clearAllMocks()
    }

    @Test
    fun `test a network failure while getting medicine list`() = runTest{

        // Setting up the mock behavior to return an error
        coEvery { repo.getMedicinesList() } returns flow { emit(Either.Error(Failure.NetworkConnection)) }

        // Calling the use case function
        val result = GetMedicinesListUseCase(repo).invoke()

        // Collecting the result inside the coroutine scope
        result.collect { either ->
            either.either(
                {
                    // Assert that the error is a NetworkConnection failure
                    Assert.assertTrue(it is Failure.NetworkConnection)
                },
                {}
            )
        }
    }


    @Test
    fun `test success with empty medicine list`() = runTest{

        coEvery { repo.getMedicinesList() } returns flow { emit(Either.Result(arrayListOf())) }

        val result = GetMedicinesListUseCase(repo).invoke()

        result.collect { either ->
            either.either(
                {
                },
                {
                    Assert.assertTrue(it.isEmpty())
                }
            )
        }
    }

    @Test
    fun `test success with medicine list`() = runTest{

        val dataList= arrayListOf<Medicine>()
        dataList.add(Medicine("diabetes","asprin","4mg","strong"))
        coEvery { repo.getMedicinesList() } returns flow { emit(Either.Result(dataList)) }

        val result = GetMedicinesListUseCase(repo).invoke()

        result.collect { either ->
            either.either(
                {
                },
                {
                    Assert.assertTrue(it.isNotEmpty())
                    Assert.assertTrue(it[0].name=="asprin")
                }
            )
        }
    }

}