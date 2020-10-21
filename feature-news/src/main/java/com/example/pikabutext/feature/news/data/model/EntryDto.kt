package com.example.pikabutext.feature.news.data.model

data class EntryDto(
    val id: Int,
    val title: String,
    val body: String? = null,
    val images: List<String>? = null
)