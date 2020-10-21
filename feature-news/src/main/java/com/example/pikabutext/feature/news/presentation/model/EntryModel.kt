package com.example.pikabutext.feature.news.presentation.model

import com.example.pikabutext.feature.news.R

interface ItemModel

data class EntryModel(
    val id: Int,
    val title: String,
    val body: String? = null,
    val images: List<String>? = null,
    val isInFavorites: Boolean
) : ItemModel {
    fun getBookmarkIcon() =
        if (isInFavorites) {
            R.drawable.ic_bookmark
        } else {
            R.drawable.ic_bookmark_border
        }
}

object EmptyModel : ItemModel
object ProgressModel : ItemModel
data class ErrorModel(val exception: Exception) : ItemModel