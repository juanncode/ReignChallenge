package com.github.juanncode.challengereign.data.datasources

import com.github.juanncode.challengereign.data.database.Hit
import com.github.juanncode.challengereign.data.service.models.hit.Hit as HitService

interface LocalDataSource {

    suspend fun insertHit(hits: List<HitService>)
    suspend fun deleteHitDb(hit: Hit)
    suspend fun getAll(): List<Hit>
}