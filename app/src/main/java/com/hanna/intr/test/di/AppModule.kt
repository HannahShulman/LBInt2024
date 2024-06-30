package com.hanna.intr.test.di

import android.app.Application
import androidx.room.Room
import com.hanna.intr.test.data.datasource.db.AppDatabase
import com.hanna.intr.test.data.datasource.db.LaunchDao
import com.hanna.intr.test.data.datasource.network.api.LaunchApi
import com.hanna.intr.test.data.repositories.LaunchesRepository
import com.hanna.intr.test.data.repositories.LaunchesRepositoryImpl
import com.hanna.intr.test.domain.usecases.FetchAllLaunchesUseCase
import com.hanna.intr.test.domain.usecases.FetchLaunchByIdUseCase
import com.hanna.intr.test.presenter.viewmodels.LaunchByIdViewModel
import com.hanna.intr.test.presenter.viewmodels.LaunchesListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    factory<LaunchesRepository> { LaunchesRepositoryImpl(get(), get()) }
    single { provideDatabase(androidApplication()) }
    single<LaunchApi> { createLaunchApi() }
    single { provideLaunchDao(get()) }
    single { LaunchesRepositoryImpl(get(), get()) }
    factory { FetchAllLaunchesUseCase(get()) }
    factory { FetchLaunchByIdUseCase(get()) }
    viewModel { LaunchesListViewModel(get()) }
    viewModel { LaunchByIdViewModel(get()) }
}

fun provideDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(application, AppDatabase::class.java, "launches_database")
        .fallbackToDestructiveMigration()
        .build()
}

fun provideLaunchDao(database: AppDatabase): LaunchDao {
    return database.launchDao()
}
fun createLaunchApi(): LaunchApi {
    return Retrofit.Builder()
        .baseUrl("https://api.spacexdata.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(LaunchApi::class.java)
}
