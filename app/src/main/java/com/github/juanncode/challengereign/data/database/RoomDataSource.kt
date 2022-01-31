package com.github.juanncode.challengereign.data.database

import com.github.juanncode.challengereign.data.datasources.LocalDataSource
import com.github.juanncode.challengereign.data.toRoomHit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.github.juanncode.challengereign.data.service.models.hit.Hit as HitService

class RoomDataSource(db: HitDataBase): LocalDataSource {
    private val hitDao = db.hitDao()
    override suspend fun insertHit(hits: List<HitService>) {
        withContext(Dispatchers.IO) {hitDao.insertHit(hits.map { it.toRoomHit() })}
    }

    override suspend fun deleteHitDb(hit: Hit) {
        withContext(Dispatchers.IO) {hitDao.updateHit(hit)}
    }

    override suspend fun getAll(): List<Hit> {
       return withContext(Dispatchers.IO) {hitDao.getAll()}
    }
}