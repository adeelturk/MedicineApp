package com.turk.medapp.data.dataSource.remote

import com.turk.medapp.data.MedicineService
import com.turk.medapp.data.dataSource.MedicineDatasource
import com.turk.medapp.data.dtos.HealthProblems
import javax.inject.Inject

class MedicineRemoteDatasourceImpl @Inject constructor(private val service: MedicineService) :
    MedicineDatasource {

    override suspend fun getHealthProblemData(): HealthProblems {
        return service.fetchMedicinesList()
    }
}