package com.hanna.intr.test.data.datasource.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hanna.intr.test.domain.models.Launch

@Dao
interface LaunchDao {
    @Query("SELECT * FROM launch")
    suspend fun getAllLaunches(): List<Launch>

    @Query("SELECT * FROM launch WHERE id=:id")
    suspend fun getLaunchById(id: String): Launch

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLaunches(launch: List<Launch>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLaunch(launch: Launch)
}