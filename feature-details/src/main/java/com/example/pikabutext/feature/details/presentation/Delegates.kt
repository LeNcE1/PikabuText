package com.example.pikabutext.feature.details.presentation

import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.example.pikabutext.feature.details.R
import com.example.pikabutext.feature.details.presentation.model.BodyModel
import com.example.pikabutext.feature.details.presentation.model.ImageModel
import com.example.pikabutext.feature.details.presentation.model.TextModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import kotlinx.android.synthetic.main.item_body.*
import kotlinx.android.synthetic.main.item_image.*

fun itemTextDelegate() =
    adapterDelegateLayoutContainer<TextModel, BodyModel>(R.layout.item_body) {
        bind {
            textBody.text = item.text
        }
    }

fun itemImageDelegate() =
    adapterDelegateLayoutContainer<ImageModel, BodyModel>(R.layout.item_image) {
        bind {
            Glide
                .with(itemView)
                .load(item.imageUrl)
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_cloud)
                .override(SIZE_ORIGINAL)
                .into(image)
        }
    }
