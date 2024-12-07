package com.turk.medapp.data.repository

import com.turk.medapp.core.Either
import com.turk.medapp.core.Failure
import com.turk.medapp.data.dtos.Medicine
import kotlinx.coroutines.flow.Flow

interface MedicineRepository {

    suspend fun getMedicinesList(): Flow<Either<Failure, List<Medicine>>>

}