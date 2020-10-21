package com.example.pikabutext.feature.database

import android.content.Context
import com.example.pikabutext.feature.database.domain.model.FavoriteDtoMapper
import com.example.pikabutext.feature.database.data.repository.AppDatabase
import com.example.pikabutext.feature.database.data.repository.DatabaseRepository
import com.example.pikabutext.feature.database.data.repository.DatabaseRepositoryImpl
import com.example.pikabutext.feature.database.domain.interactor.DatabaseInteractor
import com.example.pikabutext.feature.database.domain.interactor.DatabaseInteractorImpl
import com.example.pikabutext.feature.database.domain.model.FavoriteMapper
import toothpick.config.Module

class DataBaseModule(context: Context) : Module() {
    init {
        val favoriteDao = AppDatabase.getDatabase(context).favoriteDao()
        bind(DatabaseRepository::class.java).toInstance(DatabaseRepositoryImpl(favoriteDao))
        bind(DatabaseInteractor::class.java).to(DatabaseInteractorImpl::class.java)
        bind(FavoriteDtoMapper::class.java).toInstance(FavoriteDtoMapper())
        bind(FavoriteMapper::class.java).toInstance(FavoriteMapper())
    }
}