package com.turk.medapp.data.dtos

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class HealthProblemEntityConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromHealthProblems(value: List<HealthProblems>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toHealthProblems(value: String): List<HealthProblems>? {
        val type = object : TypeToken<List<HealthProblems>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromProblem(value: List<Problem>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toProblem(value: String): List<Problem>? {
        val type = object : TypeToken<List<Problem>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromConditions(value: Map<String, List<HealthConditionEntry>>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toConditions(value: String): Map<String, List<HealthConditionEntry>>? {
        val type = object : TypeToken<Map<String, List<HealthConditionEntry>>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromHealthConditionEntries(value: List<HealthConditionEntry>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toHealthConditionEntries(value: String): List<HealthConditionEntry>? {
        val type = object : TypeToken<List<HealthConditionEntry>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromMedicationClassTypes(value: List<MedicationClassType>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toMedicationClassTypes(value: String): List<MedicationClassType>? {
        val type = object : TypeToken<List<MedicationClassType>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromAssociatedDrugs(value: Map<String, List<Drug>>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toAssociatedDrugs(value: String): Map<String, List<Drug>>? {
        val type = object : TypeToken<Map<String, List<Drug>>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromDrugs(value: List<Drug>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toDrugs(value: String): List<Drug>? {
        val type = object : TypeToken<List<Drug>>() {}.type
        return gson.fromJson(value, type)
    }
}