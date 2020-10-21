package com.example.pikabutext.feature.news.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.pikabutext.feature.news.presentation.model.EntryModel
import com.example.pikabutext.feature.news.presentation.model.ItemModel
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class NewsAdapter(vararg delegates: AdapterDelegate<List<ItemModel>>) :
    AsyncListDifferDelegationAdapter<ItemModel>(
        object : DiffUtil.ItemCallback<ItemModel>() {
            override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean =
                if (oldItem is EntryModel && newItem is EntryModel) {
                    oldItem.id == newItem.id
                } else false

            override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean =
                if (oldItem is EntryModel && newItem is EntryModel) {
                    oldItem.hashCode() == newItem.hashCode()
                } else false
        }
    ) {
    init {
        delegates.forEach { delegatesManager.addDelegate(it) }
    }
}