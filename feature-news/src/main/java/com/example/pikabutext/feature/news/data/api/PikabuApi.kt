package com.example.pikabutext.feature.news.data.api

import com.example.pikabutext.feature.news.BuildConfig
import com.example.pikabutext.feature.news.data.model.EntryDto
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Provider

interface PikabuApi {
    @GET("feed.php")
    suspend fun getFeed(): List<EntryDto>
}

class PikabuApiProvider @Inject constructor() : Provider<PikabuApi> {
    override fun get(): PikabuApi = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(PikabuApi::class.java)
}