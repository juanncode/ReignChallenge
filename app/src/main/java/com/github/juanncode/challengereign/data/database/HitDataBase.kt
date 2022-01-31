package com.github.juanncode.challengereign.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Hit::class], version = 1)
abstract class HitDataBase: RoomDatabase() {
    abstract fun hitDao(): HitDao
}