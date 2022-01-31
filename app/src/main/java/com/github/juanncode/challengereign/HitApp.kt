package com.github.juanncode.challengereign

import android.app.Application
import androidx.room.Room
import com.github.juanncode.challengereign.data.database.HitDataBase

class HitApp: Application() {
    lateinit var db: HitDataBase
        private set

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(this, HitDataBase::class.java, "hit_db").build()
    }
}