package com.hanna.intr.test.di

import com.hanna.intr.test.data.datasource.db.LaunchDao
import com.hanna.intr.test.data.datasource.network.api.LaunchApi
import com.hanna.intr.test.data.repositories.LaunchesRepository
import com.hanna.intr.test.data.repositories.LaunchesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build();

        return Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideLaunchApi(retrofit: Retrofit): LaunchApi {
        return retrofit.create(LaunchApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLaunchesRepository(
        launchDao: LaunchDao,
        launchApi: LaunchApi
    ): LaunchesRepository {
        return LaunchesRepositoryImpl(launchApi, launchDao)
    }
}
