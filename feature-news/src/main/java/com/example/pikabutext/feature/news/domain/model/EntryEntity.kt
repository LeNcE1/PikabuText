package com.example.pikabutext.feature.news.domain.model

data class EntryEntity(
    val id: Int,
    val title: String,
    val body: String?,
    val images: List<String>?
)