package com.example.pikabutext.feature.news.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pikabutext.core.utils.map
import com.example.pikabutext.feature.database.domain.interactor.DatabaseInteractor
import com.example.pikabutext.feature.details.DetailsScreen
import com.example.pikabutext.feature.news.presentation.model.EmptyModel
import com.example.pikabutext.feature.news.presentation.model.EntryModel
import com.example.pikabutext.feature.news.presentation.model.ItemModel
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router
import javax.inject.Inject

internal class FavoritesViewModel @Inject constructor(
    private val databaseInteractor: DatabaseInteractor,
    private val router: Router
) : ViewModel() {

    val items = databaseInteractor.favorites.map { list ->
        if (list.isEmpty()) {
            listOf<ItemModel>(EmptyModel)
        } else {
            list.map {
                with(it) {
                    EntryModel(id, title, body, images, true)
                }
            }
        }
    }

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
}