package com.github.juanncode.challengereign.data.service

import com.github.juanncode.challengereign.data.service.models.hit.HitsResponse
import retrofit2.http.GET

interface HitService {

    @GET("search_by_date?query=mobile")
    suspend fun getAllHits(): HitsResponse
}