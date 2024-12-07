package com.turk.medapp.di

import com.turk.medapp.data.MedicineDataMapper
import com.turk.medapp.data.MedicineService
import com.turk.medapp.data.localPersistance.HealthProblemDao
import com.turk.medapp.data.dataSource.local.MedicineLocalDatasourceImpl
import com.turk.medapp.data.dataSource.remote.MedicineRemoteDatasourceImpl
import com.turk.medapp.data.repository.MedicineRepository
import com.turk.medapp.data.repository.MedicineRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providesMedicineRemoteDataSource(
        apiService:MedicineService,
    ): MedicineRemoteDatasourceImpl {
        return MedicineRemoteDatasourceImpl(apiService)
    }

    @Provides
    fun providesMedicineLocalDataSource(
        healthProblemDao: HealthProblemDao
    ): MedicineLocalDatasourceImpl {
        return MedicineLocalDatasourceImpl(healthProblemDao)
    }

    @Provides
    fun providesMedicineRemoteRepository( medicineDatasource: MedicineRemoteDatasourceImpl,
                                          localDataSource: MedicineLocalDatasourceImpl,
                                          mapper: MedicineDataMapper,
                                          healthProblemDao: HealthProblemDao
    ): MedicineRepository{

        return MedicineRepositoryImpl(medicineDatasource,localDataSource,mapper, healthProblemDao)
    }

    @Provides
    @Singleton
    fun provideMedicineService(retrofit: Retrofit): MedicineService {
        return retrofit.create(MedicineService::class.java)
    }

}