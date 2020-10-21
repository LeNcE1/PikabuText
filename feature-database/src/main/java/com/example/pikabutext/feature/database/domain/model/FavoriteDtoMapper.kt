package com.example.pikabutext.feature.database.domain.model

import com.example.pikabutext.feature.database.data.model.FavoriteDto


class FavoriteDtoMapper {
    fun map(favoriteDto: FavoriteDto) = with(favoriteDto.entry) {
        FavoriteEntity(id, title, body, favoriteDto.images)
    }
}