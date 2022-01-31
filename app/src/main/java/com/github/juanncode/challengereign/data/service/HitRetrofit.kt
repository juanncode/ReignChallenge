package com.github.juanncode.challengereign.data.service
import com.github.juanncode.challengereign.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HitRetrofit {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: HitService = retrofit.create(HitService::class.java)
}