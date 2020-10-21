package com.example.pikabutext.feature.news

import com.example.pikabutext.feature.news.data.api.PikabuApi
import com.example.pikabutext.feature.news.domain.model.EntryDtoMapper
import com.example.pikabutext.feature.news.data.repository.NewsRepository
import com.example.pikabutext.feature.news.data.repository.NewsRepositoryImpl
import com.example.pikabutext.feature.news.domain.interactor.NewsInteractor
import com.example.pikabutext.feature.news.domain.interactor.NewsInteractorImpl
import com.example.pikabutext.feature.news.presentation.NewsViewModel
import com.example.pikabutext.feature.news.presentation.model.EntryEntityMapper
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import toothpick.config.Module

class NewsModule : Module() {
    init {
        val pikabuApi = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(PikabuApi::class.java)
        bind(PikabuApi::class.java).toInstance(pikabuApi)
        bind(NewsRepository::class.java).to(NewsRepositoryImpl::class.java).singleton()
        bind(EntryDtoMapper::class.java).toInstance(EntryDtoMapper())
        bind(EntryEntityMapper::class.java).toInstance(EntryEntityMapper())
        bind(NewsInteractor::class.java).to(NewsInteractorImpl::class.java)
        bind(NewsViewModel::class.java)
    }
}
