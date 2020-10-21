package com.example.pikabutext.feature.details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pikabutext.core.utils.map
import com.example.pikabutext.core.utils.readOnly
import com.example.pikabutext.feature.database.domain.interactor.DatabaseInteractor
import com.example.pikabutext.feature.details.presentation.model.BodyModel
import com.example.pikabutext.feature.details.presentation.model.DetailsModel
import com.example.pikabutext.feature.details.presentation.model.ImageModel
import com.example.pikabutext.feature.details.presentation.model.TextModel
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router
import javax.inject.Inject

internal class DetailsViewModel @Inject constructor(
    private val databaseInteractor: DatabaseInteractor,
    private val router: Router,
    private val detailsModel: DetailsModel
) : ViewModel() {

    private val _title = MutableLiveData<String>(detailsModel.title)
    val title: LiveData<String> = _title.readOnly()

    val bookmarkIcon =
        databaseInteractor.favorites.map { list ->
            detailsModel.isInFavorites = list.any { it.id == detailsModel.id }
            detailsModel.getBookmarkIcon()
        }

    val items = MutableLiveData<List<BodyModel>>(createItems())

    val adapter = ListDelegationAdapter<List<BodyModel>>(
        itemTextDelegate(),
        itemImageDelegate()
    )

    private fun createItems(): List<BodyModel> {
        val list: MutableList<BodyModel> = mutableListOf()
        detailsModel.body?.let { if (it.isNotEmpty()) list.add(TextModel(it)) }
        detailsModel.images?.map { if (it.isNotEmpty()) list.add(ImageModel(it)) }
        return list
    }

    fun onBookmarkClick() {
        viewModelScope.launch {
            with(detailsModel) {
                if (!isInFavorites) {
                    databaseInteractor.saveEntry(id, title, body, images)
                } else {
                    databaseInteractor.deleteEntry(id)
                }
            }
        }
    }

    fun goBack() {
        router.exit()
    }
}