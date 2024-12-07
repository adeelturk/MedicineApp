package com.turk.medapp.data.dataSource.local

import com.turk.medapp.data.localPersistance.HealthProblemDao
import com.turk.medapp.data.dataSource.MedicineDatasource
import com.turk.medapp.data.dtos.HealthProblems
import com.turk.medapp.core.utils.DataNotFoundException
import javax.inject.Inject

class MedicineLocalDatasourceImpl @Inject constructor(private val healthProblemDao: HealthProblemDao) : MedicineDatasource {

    override suspend fun getHealthProblemData(): HealthProblems {
        return healthProblemDao.getMedicines() ?: throw DataNotFoundException("HealthProblems data not found")
    }
}