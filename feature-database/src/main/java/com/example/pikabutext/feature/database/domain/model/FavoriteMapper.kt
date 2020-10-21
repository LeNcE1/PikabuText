package com.example.pikabutext.feature.database.domain.model

import com.example.pikabutext.feature.database.data.model.FavoriteDto
import com.example.pikabutext.feature.database.data.model.FavoriteEntryDto

class FavoriteMapper {
    fun map(id: Int, title: String, body: String?, images: List<String>?) =
        FavoriteDto(FavoriteEntryDto(id, title, body), images)
}