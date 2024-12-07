package com.turk.medapp.core.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.turk.medapp.data.dtos.Drug
import com.turk.medapp.data.dtos.HealthConditionEntry
import com.turk.medapp.data.dtos.HealthProblems
import com.turk.medapp.data.dtos.MedicationClassType
import com.turk.medapp.data.dtos.Problem
import java.lang.reflect.Type

class HealthProblemsDeserializer : JsonDeserializer<HealthProblems> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): HealthProblems {
        val jsonObject = json.asJsonObject
        val problemsArray = jsonObject["problems"]?.asJsonArray ?: return HealthProblems(problems = emptyList())
        val problemEntries = mutableListOf<Problem>()

        // Loop through each problem entry in the JSON array
        for (problem in problemsArray) {
            val conditionsMap = mutableMapOf<String, List<HealthConditionEntry>>()
            val problemObject = problem.asJsonObject

            // Iterate over dynamic conditions (like Diabetes, Asthma)
            for ((key, value) in problemObject.entrySet()) {
                val conditionEntries = value.asJsonArray.map { conditionJson ->
                    context.deserialize<HealthConditionEntry>(conditionJson, HealthConditionEntry::class.java)
                }
                conditionsMap[key] = conditionEntries
            }

            problemEntries.add(Problem(conditions = conditionsMap))
        }

        return HealthProblems(problems = problemEntries)
    }
}

class HealthConditionEntryDeserializer : JsonDeserializer<HealthConditionEntry> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): HealthConditionEntry {
        val jsonObject = json.asJsonObject
        val medicationsJsonArray = jsonObject["medications"]?.asJsonArray
        val labsJsonArray = jsonObject["labs"]?.asJsonArray

        // Deserialize medications if available
        val medications = medicationsJsonArray?.map { medicationJson ->
            context.deserialize<MedicationClassType>(medicationJson, MedicationClassType::class.java)
        }

        // Deserialize labs if available
        val labs = labsJsonArray?.map { labJson ->
            context.deserialize<Map<String, String>>(labJson, Map::class.java)
        }

        return HealthConditionEntry(medications = medications)
    }
}

class MedicationGroupDeserializer : JsonDeserializer<MedicationClassType> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): MedicationClassType {
        val associatedDrugs = mutableMapOf<String, List<Drug>>()
        val jsonObject = json.asJsonObject
        val getMedicationJsonList=jsonObject.get("medicationsClasses").asJsonArray
        getMedicationJsonList.forEach {
            for ((classNameKey, classNameValue) in it.asJsonObject.entrySet()) {
                if (classNameKey.startsWith("className")) {

                    val classnameList=classNameValue.asJsonArray
                    classnameList.forEach {

                        for ((key, value) in it.asJsonObject.entrySet()) {
                            if (key.startsWith("associatedDrug")) { // Match keys like "associatedDrug" or "associatedDrug#2"
                                val drugList = value.asJsonArray.map { drugJson ->
                                    context.deserialize<Drug>(drugJson, Drug::class.java)
                                }
                                associatedDrugs[classNameKey+key] = drugList
                            }
                        }
                    }

                }

            }
        }

        // Iterate over the dynamic keys in the JSON object


        return MedicationClassType(associatedDrugs = associatedDrugs)
    }
}

