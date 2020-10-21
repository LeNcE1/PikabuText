package com.example.pikabutext.feature.details.presentation.model

import android.os.Parcelable
import com.example.pikabutext.feature.details.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailsModel(
    val id: Int,
    val title: String,
    val body: String? = null,
    val images: List<String>? = null,
    var isInFavorites: Boolean
) : Parcelable {
    fun getBookmarkIcon() =
        if (isInFavorites) {
            R.drawable.ic_bookmark
        } else {
            R.drawable.ic_bookmark_border
        }

}