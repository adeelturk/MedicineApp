package com.turk.medapp.data.dataSource

import com.turk.medapp.data.dtos.HealthProblems

interface MedicineDatasource {

    suspend fun getHealthProblemData(): HealthProblems

}