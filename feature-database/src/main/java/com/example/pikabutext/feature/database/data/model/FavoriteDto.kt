package com.example.pikabutext.feature.database.data.model

import androidx.room.Embedded
import androidx.room.Relation


data class FavoriteDto(
    @Embedded
    val entry: FavoriteEntryDto,
    @Relation(
        parentColumn = "id",
        entityColumn = "entryId",
        entity = FavoriteImageDto::class,
        projection = ["url"]
    )
    val images: List<String>? = null
)