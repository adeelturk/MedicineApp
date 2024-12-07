package com.turk.medapp.data

import com.turk.medapp.data.dtos.HealthProblems
import retrofit2.Response
import retrofit2.http.GET

interface MedicineService {

    @GET("48e2a9d2-6dc7-4adf-97a1-1acb75bb5ce8")
    suspend fun fetchMedicinesList(): HealthProblems
}