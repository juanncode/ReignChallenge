package com.github.juanncode.challengereign.data.repository

import android.util.Log
import com.github.juanncode.challengereign.data.datasources.LocalDataSource
import com.github.juanncode.challengereign.data.datasources.RemoteDataSource
import com.github.juanncode.challengereign.data.database.Hit as HitDb

class HitRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    suspend fun deleteHitDb(hit: HitDb): List<HitDb> {
        localDataSource.deleteHitDb(hit.copy(isDeleted = true))
        return localDataSource.getAll().filter { !it.isDeleted }
    }

    suspend fun getAllHits(): List<HitDb> {
        try {

            val listHitsOfService = remoteDataSource.getAllHits().hits
            val listHitsOfDb = localDataSource.getAll()
            if (listHitsOfDb.isEmpty()) {
                localDataSource.insertHit(listHitsOfService)

            } else {
                listHitsOfService.forEach { serviceHint ->
                    val findHit =
                        listHitsOfDb.firstOrNull { it.created_at_i == serviceHint.created_at_i }
                    if (findHit == null) {
                        localDataSource.insertHit(listOf(serviceHint))
                    }
                }
            }

            return localDataSource.getAll().filter { !it.isDeleted }
        }
        catch (exception: Exception) {
            Log.e("EXCEPTION", "-> ${exception.message}")
            return localDataSource.getAll().filter { !it.isDeleted }
        }
    }
}