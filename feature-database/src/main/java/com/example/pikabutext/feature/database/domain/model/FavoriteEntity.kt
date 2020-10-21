package com.example.pikabutext.feature.database.domain.model

data class FavoriteEntity(
    val id: Int,
    val title: String,
    val body: String?,
    val images: List<String>?
)