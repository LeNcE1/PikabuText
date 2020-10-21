package com.example.pikabutext.feature.news.domain.interactor

import com.example.pikabutext.feature.news.data.repository.NewsRepository
import com.example.pikabutext.feature.news.domain.model.EntryDtoMapper
import com.example.pikabutext.feature.news.domain.model.EntryEntity
import javax.inject.Inject

interface NewsInteractor {
    suspend fun getFeed(): List<EntryEntity>
    val cacheFeed: List<EntryEntity>
}

class NewsInteractorImpl @Inject constructor(
    private val newsRepository: NewsRepository,
    private val entryDtoMapper: EntryDtoMapper
) : NewsInteractor {
    private var _cacheFeed: List<EntryEntity> = emptyList()

    override val cacheFeed: List<EntryEntity>
        get() = _cacheFeed

    override suspend fun getFeed(): List<EntryEntity> {
        _cacheFeed = newsRepository.getFeed().map { entryDtoMapper.map(it) }
        return _cacheFeed
    }
}