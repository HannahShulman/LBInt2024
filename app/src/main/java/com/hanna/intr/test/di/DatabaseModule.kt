package com.hanna.intr.test.di

import android.content.Context
import androidx.room.Room
import com.hanna.intr.test.data.datasource.db.AppDatabase
import com.hanna.intr.test.data.datasource.db.LaunchDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "spacex_launches_database"
        ).build()
    }

    @Provides
    fun provideLaunchDao(appDatabase: AppDatabase): LaunchDao {
        return appDatabase.launchDao()
    }
}