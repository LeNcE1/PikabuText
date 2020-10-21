package com.example.pikabutext.feature.news.presentation.model

import com.example.pikabutext.feature.news.domain.model.EntryEntity

class EntryEntityMapper {
    fun map(entryEntity: EntryEntity, isInFavorites: Boolean) = with(entryEntity) {
        EntryModel(id, title, body, images, isInFavorites)
    }
}