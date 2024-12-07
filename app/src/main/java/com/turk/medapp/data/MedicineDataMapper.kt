package com.turk.medapp.data

import com.turk.medapp.core.DataMapper
import com.turk.medapp.data.dtos.HealthProblems
import com.turk.medapp.data.dtos.Medicine
import javax.inject.Inject

class MedicineDataMapper  @Inject constructor() : DataMapper<HealthProblems, List<Medicine>>() {

    override fun mapToDomainModel(serverResponseObj: HealthProblems): List<Medicine> {

        val medicines = arrayListOf<Medicine>()
        serverResponseObj.problems?.forEach { problem ->

            problem.conditions?.forEach { condition ->

                condition.value.forEach { medication ->

                    medication.medications?.forEach { medicationByType ->
                        medicationByType.associatedDrugs?.forEach { (key, value) ->
                            value.forEach { drugs ->
                                medicines.add(
                                    Medicine(
                                        illnessName = condition.key,
                                        name=drugs.name.orEmpty(),
                                        dose=drugs.dose.orEmpty(),
                                        strength = drugs.strength.orEmpty()
                                    )
                                )
                            }
                        }
                    }
                }
            }

        }

        return medicines
    }
}