package com.hanna.intr.test.data.datasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hanna.intr.test.domain.models.Launch

@Database(entities = [Launch::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun launchDao(): LaunchDao
}