package com.example.myapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface historyViewDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(historyResult : History)

    @Query("SELECT * FROM history_data")
    suspend fun getAll(): List<History>

}