package com.turk.medapp.core.utils

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.turk.medapp.data.dtos.Medicine

class MedicineNavType : NavType<Medicine>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Medicine? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Medicine {
        return Gson().fromJson(value, Medicine::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Medicine) {
        bundle.putParcelable(key, value)
    }
}