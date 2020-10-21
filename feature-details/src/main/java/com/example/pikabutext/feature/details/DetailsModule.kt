package com.example.pikabutext.feature.details

import com.example.pikabutext.feature.details.presentation.DetailsViewModel
import toothpick.config.Module

class DetailsModule : Module() {
    init {
        bind(DetailsViewModel::class.java)
    }
}
