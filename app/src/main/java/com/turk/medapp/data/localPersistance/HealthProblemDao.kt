package com.turk.medapp.data.localPersistance

import androidx.room.Dao
import androidx.room.Query
import com.turk.medapp.core.BaseDao
import com.turk.medapp.data.dtos.HealthProblems


@Dao
interface HealthProblemDao: BaseDao<HealthProblems> {
    @Query("SELECT * FROM HealthProblems LIMIT 1")
     fun getMedicines(): HealthProblems?

}