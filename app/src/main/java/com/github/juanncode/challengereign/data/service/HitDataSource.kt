package com.github.juanncode.challengereign.data.service

import com.github.juanncode.challengereign.data.datasources.RemoteDataSource
import com.github.juanncode.challengereign.data.service.models.hit.HitsResponse

class HitDataSource: RemoteDataSource {
    override suspend fun getAllHits(): HitsResponse = HitRetrofit.service.getAllHits()
}