package com.example.myprojectjavaonkotlin.data.room

import androidx.room.Dao
import androidx.room.Query

@Dao
interface HistoryDao {

    @Query("SELECT * FROM history")
    fun getAll(): List<HistoryEntity>
}