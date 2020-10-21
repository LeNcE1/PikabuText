package com.example.pikabutext.feature.news.data.api

import com.example.pikabutext.feature.news.data.model.EntryDto
import retrofit2.http.GET

interface PikabuApi {
    @GET("feed.php")
    suspend fun getFeed(): List<EntryDto>
}