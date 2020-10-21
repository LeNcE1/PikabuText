package com.example.pikabutext.feature.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.pikabutext.feature.details.presentation.DetailsFragment
import com.example.pikabutext.feature.details.presentation.model.DetailsModel
import ru.terrakok.cicerone.android.support.SupportAppScreen

class DetailsScreen(
    val id: Int,
    val title: String,
    val body: String? = null,
    val images: List<String>? = null,
    private val isInFavorites: Boolean
) : SupportAppScreen() {
    override fun getFragment(): Fragment {
        val args = Bundle().apply {
            putParcelable(
                DetailsFragment::class.java.name,
                DetailsModel(id, title, body, images, isInFavorites)
            )
        }
        return DetailsFragment().apply { arguments = args }
    }
}