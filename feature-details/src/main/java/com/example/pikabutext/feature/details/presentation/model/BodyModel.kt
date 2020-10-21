package com.example.pikabutext.feature.details.presentation.model

interface BodyModel

data class TextModel(val text: String) : BodyModel
data class ImageModel(val imageUrl: String) : BodyModel