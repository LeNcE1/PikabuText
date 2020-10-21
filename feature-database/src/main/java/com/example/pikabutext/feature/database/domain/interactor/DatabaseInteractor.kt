package com.example.pikabutext.feature.database.domain.interactor

import androidx.lifecycle.LiveData
import com.example.pikabutext.core.utils.map
import com.example.pikabutext.feature.database.domain.model.FavoriteDtoMapper
import com.example.pikabutext.feature.database.data.repository.DatabaseRepository
import com.example.pikabutext.feature.database.domain.model.FavoriteEntity
import com.example.pikabutext.feature.database.domain.model.FavoriteMapper
import javax.inject.Inject

interface DatabaseInteractor {
    val favorites: LiveData<List<FavoriteEntity>>
    suspend fun getFavorites(): List<FavoriteEntity>
    suspend fun saveEntry(
        id: Int,
        title: String,
        body: String? = null,
        images: List<String>? = null
    )

    suspend fun deleteEntry(id: Int)
}

class DatabaseInteractorImpl @Inject constructor(
    private val databaseRepository: DatabaseRepository,
    private val favoriteDtoMapper: FavoriteDtoMapper,
    private val favoriteMapper: FavoriteMapper
) : DatabaseInteractor {
    override val favorites: LiveData<List<FavoriteEntity>> =
        databaseRepository.favorites.map { list -> list.map { favoriteDtoMapper.map(it) } }

    override suspend fun getFavorites(): List<FavoriteEntity> =
        databaseRepository.getFavorites().map { favoriteDtoMapper.map(it) }

    override suspend fun saveEntry(id: Int, title: String, body: String?, images: List<String>?) {
        databaseRepository.insert(favoriteMapper.map(id, title, body, images))
    }

    override suspend fun deleteEntry(id: Int) {
        databaseRepository.delete(id)
    }

}