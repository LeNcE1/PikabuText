package com.example.pikabutext.feature.news.domain.model

import com.example.pikabutext.feature.news.data.model.EntryDto

class EntryDtoMapper {
    fun map(entryDto: EntryDto) = with(entryDto) {
        EntryEntity(id, title, body, images)
    }
}