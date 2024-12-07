package com.turk.medapp.di


import android.util.Log
import com.google.gson.GsonBuilder
import com.turk.medapp.data.dtos.HealthConditionEntry
import com.turk.medapp.data.dtos.HealthProblems
import com.turk.medapp.data.dtos.MedicationClassType
import com.turk.medapp.core.utils.HealthConditionEntryDeserializer
import com.turk.medapp.core.utils.HealthProblemsDeserializer
import com.turk.medapp.core.utils.MedicationGroupDeserializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        val logger =
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        httpClient.addInterceptor(logger)
        httpClient.addInterceptor( object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {

                Log.v("adee","")

                val response= chain.proceed(chain.request())
                return response
            }


        })
        val gson = GsonBuilder()
            .registerTypeAdapter(HealthProblems::class.java, HealthProblemsDeserializer())
            .registerTypeAdapter(HealthConditionEntry::class.java, HealthConditionEntryDeserializer())
            .registerTypeAdapter(MedicationClassType::class.java, MedicationGroupDeserializer())
            .create()
        val retroBuilder =
            Retrofit.Builder()
                .baseUrl("https://run.mocky.io/v3/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
        retroBuilder.client(
            httpClient.readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS).build()
        )
        return retroBuilder.build()
    }



}