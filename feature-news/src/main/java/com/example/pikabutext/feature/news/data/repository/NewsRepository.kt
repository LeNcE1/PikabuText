package com.example.pikabutext.feature.news.data.repository

import com.example.pikabutext.feature.news.data.api.PikabuApi
import com.example.pikabutext.feature.news.data.model.EntryDto
import javax.inject.Inject

interface NewsRepository {
    suspend fun getFeed(): List<EntryDto>
}

class NewsRepositoryImpl @Inject constructor(
    private val pikabuApi: PikabuApi
) : NewsRepository {
    override suspend fun getFeed() = pikabuApi.getFeed()
}