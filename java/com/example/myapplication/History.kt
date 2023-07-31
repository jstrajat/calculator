package com.example.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "history_data")
data class History (
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val equation : String,
//    val result : String
    )