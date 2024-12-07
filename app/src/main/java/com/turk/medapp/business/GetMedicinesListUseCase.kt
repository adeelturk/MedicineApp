package com.turk.medapp.business

import com.turk.medapp.data.repository.MedicineRepository
import javax.inject.Inject

class GetMedicinesListUseCase @Inject constructor(private val repo: MedicineRepository) {

    suspend operator fun invoke()=
        repo.getMedicinesList()

}