package com.turk.medapp.data.repository

import com.turk.medapp.core.Either
import com.turk.medapp.core.Failure
import com.turk.medapp.data.MedicineDataMapper
import com.turk.medapp.data.localPersistance.HealthProblemDao
import com.turk.medapp.data.dataSource.MedicineDatasource
import com.turk.medapp.data.dtos.Medicine
import com.turk.medapp.core.extension.asEither
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MedicineRepositoryImpl @Inject constructor(
    private val medicineDatasource: MedicineDatasource,
    private val localDataSource: MedicineDatasource,
    private val mapper: MedicineDataMapper,
    private val healthProblemDao: HealthProblemDao
) : MedicineRepository {

    override suspend fun getMedicinesList(): Flow<Either<Failure, List<Medicine>>> {
        return flow {
            // Fetch remote data
            val data = medicineDatasource.getHealthProblemData()
            emit(data)
        }.onEach { data ->
            // Save fetched data into the database
            healthProblemDao.insert(data)
        }.map { data ->
            // Fetch the updated local data aproblems = {ArrayList@34099}  size = 1nd map to domain model
            val localData = localDataSource.getHealthProblemData()
            mapper.mapToDomainModel(localData)
        }.asEither()
    }

}