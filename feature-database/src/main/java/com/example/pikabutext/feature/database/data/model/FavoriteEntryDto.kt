package com.example.pikabutext.feature.database.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteEntryDto(
    @PrimaryKey
    val id: Int,
    val title: String,
    val body: String? = null
)