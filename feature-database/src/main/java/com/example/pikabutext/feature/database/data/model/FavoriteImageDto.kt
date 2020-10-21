package com.example.pikabutext.feature.database.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteImageDto(
    var url: String,
    var entryId: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}