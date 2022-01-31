package com.github.juanncode.challengereign.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface HitDao {
    @Insert
    fun insertHit(hit: List<Hit>)

    @Update
    fun updateHit(hit: Hit)

    @Query("SELECT * FROM Hit")
    fun getAll(): List<Hit>

}