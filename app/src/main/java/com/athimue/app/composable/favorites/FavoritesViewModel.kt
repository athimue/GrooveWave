package com.athimue.app.composable.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.app.composable.search.SearchResultModel
import com.athimue.domain.model.Track
import com.athimue.domain.usecase.getfavoritetracks.GetFavoriteTracksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class FavoritesViewModel @Inject constructor(
    private val getFavoriteTracksUseCase: GetFavoriteTracksUseCase,
) : ViewModel() {

    var uiState = MutableStateFlow(FavoritesUiState())

    init {
        viewModelScope.launch {
            getFavoriteTracksUseCase.invoke().collect {
                uiState.value =
                    uiState.value.copy(favoriteTracks = it.map { it.toSearchResultModel() })
            }
        }
    }

    private fun Track.toSearchResultModel(): SearchResultModel = SearchResultModel(
        id = id,
        title = title,
        subTitle = artist.name,
        picture = cover,
    )
}