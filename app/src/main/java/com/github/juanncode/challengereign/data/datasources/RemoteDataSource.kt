package com.github.juanncode.challengereign.data.datasources

import com.github.juanncode.challengereign.data.service.models.hit.HitsResponse


interface RemoteDataSource {

    suspend fun getAllHits(): HitsResponse
}