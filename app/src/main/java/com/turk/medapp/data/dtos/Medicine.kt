package com.turk.medapp.data.dtos

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize

data class Medicine(
    val illnessName: String,
    val name: String,
    val dose: String,
    val strength: String
) : Parcelable
