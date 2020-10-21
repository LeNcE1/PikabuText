package com.example.pikabutext.feature.news.presentation

import com.example.pikabutext.feature.news.R
import com.example.pikabutext.feature.news.databinding.ItemEntryBinding
import com.example.pikabutext.feature.news.presentation.model.*
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import kotlinx.android.synthetic.main.item_error.*
import java.net.UnknownHostException

interface ItemClickListener {
    fun onItemClick(item: EntryModel)
    fun onBookmarkClick(item: EntryModel, isCheck: Boolean)
}

fun itemEntryDelegate(listener: ItemClickListener) =
    adapterDelegateViewBinding<EntryModel, ItemModel, ItemEntryBinding>(
        { layoutInflater, root -> ItemEntryBinding.inflate(layoutInflater, root, false) }
    ) {
        bind {
            binding.titleValue = item.title
            binding.textValue = item.body
            binding.bookmark.setImageResource(item.getBookmarkIcon())

            binding.bookmark.setOnClickListener {
                listener.onBookmarkClick(item, !item.isInFavorites)
            }
            binding.root.setOnClickListener {
                listener.onItemClick(item)
            }
        }
    }

fun itemErrorDelegate() =
    adapterDelegateLayoutContainer<ErrorModel, ItemModel>(R.layout.item_error) {
        bind {
            errorText.text = when (item.exception) {
                is UnknownHostException -> getString(R.string.connection_error)
                else -> item.exception.localizedMessage
            }
        }
    }

fun itemEmptyDelegate() =
    adapterDelegateLayoutContainer<EmptyModel, ItemModel>(R.layout.item_empty) {}

fun itemProgressDelegate() =
    adapterDelegateLayoutContainer<ProgressModel, ItemModel>(R.layout.item_progress) {}