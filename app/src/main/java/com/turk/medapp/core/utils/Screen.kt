package com.turk.medapp.core.utils

import android.net.Uri
import com.google.gson.Gson
import com.turk.medapp.data.dtos.Medicine


sealed class Screen(val route: String) {
    object LoginScreen : Screen("loginScreen")
    object MedicineListScreen : Screen("medicineListScreen")
    object MedicineDetailedScreen : Screen("medicineDetailedScreen/{data}") {
        fun createRoute(data: Medicine) :String{
            val json = Uri.encode(Gson().toJson(data))
           return "medicineDetailedScreen/$json"
        }
    }
}