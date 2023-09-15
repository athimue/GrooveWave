package com.athimue.app.composable.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.app.composable.search.SearchResultModel
import com.athimue.domain.model.Album
import com.athimue.domain.model.Artist
import com.athimue.domain.model.Track
import com.athimue.domain.usecase.getFavoriteAlbums.GetFavoriteAlbumsUseCase
import com.athimue.domain.usecase.getFavoriteArtists.GetFavoriteArtistsUseCase
import com.athimue.domain.usecase.getFavoriteTracks.GetFavoriteTracksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class FavoritesViewModel @Inject constructor(
    private val getFavoriteTracksUseCase: GetFavoriteTracksUseCase,
    private val getFavoriteArtistsUseCase: GetFavoriteArtistsUseCase,
    private val getFavoriteAlbumsUseCase: GetFavoriteAlbumsUseCase,
) : ViewModel() {

    var uiState = MutableStateFlow(FavoritesUiState())

    init {
        viewModelScope.launch {
            getFavoriteArtistsUseCase.invoke().collect {
                uiState.value =
                    uiState.value.copy(favoriteArtists = it.map { artist -> artist.toSearchResultModel() })
            }
        }
        viewModelScope.launch {
            getFavoriteTracksUseCase.invoke().collect {
                uiState.value =
                    uiState.value.copy(favoriteTracks = it.map { track -> track.toSearchResultModel() })
            }
        }
        viewModelScope.launch {
            getFavoriteAlbumsUseCase.invoke().collect {
                uiState.value =
                    uiState.value.copy(favoriteAlbums = it.map { album -> album.toSearchResultModel() })
            }
        }
    }

    private fun Track.toSearchResultModel(): SearchResultModel = SearchResultModel(
        id = id,
        title = title,
        subTitle = artist.name,
        picture = cover,
    )

    private fun Artist.toSearchResultModel(): SearchResultModel = SearchResultModel(
        id = id,
        title = name,
        subTitle = id.toString(),
        picture = cover ?: "",
    )

    private fun Album.toSearchResultModel(): SearchResultModel = SearchResultModel(
        id = id,
        title = name,
        subTitle = id.toString(),
        picture = cover,
    )
}