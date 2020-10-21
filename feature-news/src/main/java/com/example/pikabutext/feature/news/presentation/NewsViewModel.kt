package com.example.pikabutext.feature.news.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pikabutext.core.utils.map
import com.example.pikabutext.feature.database.domain.interactor.DatabaseInteractor
import com.example.pikabutext.feature.database.domain.model.FavoriteEntity
import com.example.pikabutext.feature.details.DetailsScreen
import com.example.pikabutext.feature.news.domain.interactor.NewsInteractor
import com.example.pikabutext.feature.news.domain.model.EntryEntity
import com.example.pikabutext.feature.news.presentation.model.EntryEntityMapper
import com.example.pikabutext.feature.news.presentation.model.*
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router
import java.lang.Exception
import javax.inject.Inject

internal class NewsViewModel @Inject constructor(
    private val newsInteractor: NewsInteractor,
    private val entryEntityMapper: EntryEntityMapper,
    private val databaseInteractor: DatabaseInteractor,
    private val router: Router
) : ViewModel() {

    val items = MutableLiveData<List<ItemModel>>(listOf<ItemModel>(EmptyModel))

    private val itemEntryDelegate = itemEntryDelegate(object : ItemClickListener {
        override fun onItemClick(item: EntryModel) {
            with(item) {
                router.navigateTo(DetailsScreen(id, title, body, images, isInFavorites))
            }
        }

        override fun onBookmarkClick(item: EntryModel, isCheck: Boolean) {
            viewModelScope.launch {
                with(item) {
                    if (isCheck) {
                        databaseInteractor.saveEntry(id, title, body, images)
                    } else {
                        databaseInteractor.deleteEntry(id)
                    }
                }
            }
        }
    })

    val adapter = NewsAdapter(
        itemEntryDelegate,
        itemEmptyDelegate(),
        itemProgressDelegate(),
        itemErrorDelegate()
    )

    init {
        load()
        databaseInteractor.favorites.map { list ->
            if (newsInteractor.cacheFeed.isNotEmpty()) {
                val newItems = processingFavorites(newsInteractor.cacheFeed, list)
                items.value = newItems
            }
        }
    }

    fun load() {
        viewModelScope.launch {
            items.value = listOf<ItemModel>(ProgressModel)
            try {
                val feed = newsInteractor.getFeed()
                if (feed.isEmpty()) {
                    items.value = listOf<ItemModel>(EmptyModel)
                } else {
                    val favorites = databaseInteractor.getFavorites()
                    items.value = processingFavorites(feed, favorites)
                }
            } catch (exception: Exception) {
                items.value = listOf<ItemModel>(ErrorModel(exception))
            }
        }
    }

    private fun processingFavorites(
        feed: List<EntryEntity>,
        favorites: List<FavoriteEntity>
    ): List<EntryModel> {
        return feed.map { entry ->
            entryEntityMapper.map(
                entry,
                favorites.any { it.id == entry.id }
            )
        }
    }
}