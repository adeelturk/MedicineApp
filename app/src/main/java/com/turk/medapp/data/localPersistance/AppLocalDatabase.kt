
package com.turk.medapp.data.localPersistance


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.turk.medapp.data.dtos.HealthProblemEntityConverter
import com.turk.medapp.data.dtos.HealthProblems

@Database(entities = [HealthProblems::class], version = 1, exportSchema = false)
@TypeConverters(HealthProblemEntityConverter::class)
abstract class AppLocalDatabase: RoomDatabase() {
    abstract fun healthProblemDao(): HealthProblemDao
}