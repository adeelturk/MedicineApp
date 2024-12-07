package com.turk.medapp.data.dtos

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
@Entity
@TypeConverters(HealthProblemEntityConverter::class)
data class HealthProblems(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val problems: List<Problem>?
) : Parcelable

@Keep
@Parcelize
data class Problem(
    val conditions: Map<String, List<HealthConditionEntry>>? = null
) : Parcelable


@Keep
@Parcelize
data class HealthConditionEntry(
    val medications: List<MedicationClassType>? = null,
) : Parcelable

@Keep
@Parcelize
data class MedicationClassType(
    val associatedDrugs: Map<String, List<Drug>>? = null
) : Parcelable

@Keep
@Parcelize
data class Drug(
    val name: String? = null,
    val dose: String? = null,
    val strength: String? = null
) : Parcelable





