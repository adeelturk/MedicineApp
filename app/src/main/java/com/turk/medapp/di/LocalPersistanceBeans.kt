package com.turk.medapp.di

import android.content.Context
import androidx.room.Room
import com.turk.medapp.data.localPersistance.HealthProblemDao
import com.turk.medapp.data.localPersistance.AppLocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalPersistenceModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppLocalDatabase {
        return Room.databaseBuilder(
            context,
            AppLocalDatabase::class.java,
            "medicineDb"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideHealthProblemDao(database: AppLocalDatabase): HealthProblemDao {
        return database.healthProblemDao()
    }
}
