package com.athimue.app.composable.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.app.composable.search.SearchResultModel
import com.athimue.domain.model.Album
import com.athimue.domain.model.Artist
import com.athimue.domain.model.Track
import com.athimue.domain.usecase.deleteFavoriteAlbum.DeleteFavoriteAlbumUseCase
import com.athimue.domain.usecase.deleteFavoriteArtist.DeleteFavoriteArtistUseCase
import com.athimue.domain.usecase.deleteFavoriteTrack.DeleteFavoriteTrackUseCase
import com.athimue.domain.usecase.getFavoriteAlbums.GetFavoriteAlbumsUseCase
import com.athimue.domain.usecase.getFavoriteArtists.GetFavoriteArtistsUseCase
import com.athimue.domain.usecase.getFavoriteTracks.GetFavoriteTracksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
internal class FavoritesViewModel @Inject constructor(
    private val getFavoriteTracksUseCase: GetFavoriteTracksUseCase,
    private val getFavoriteArtistsUseCase: GetFavoriteArtistsUseCase,
    private val getFavoriteAlbumsUseCase: GetFavoriteAlbumsUseCase,
    private val deleteFavoriteTrackUseCase: DeleteFavoriteTrackUseCase,
    private val deleteFavoriteArtistUseCase: DeleteFavoriteArtistUseCase,
    private val deleteFavoriteAlbumUseCase: DeleteFavoriteAlbumUseCase
) : ViewModel() {

    var uiState = MutableStateFlow(FavoritesUiState())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoriteArtistsUseCase.invoke().collect {
                withContext(Dispatchers.Main) {
                    uiState.value =
                        uiState.value.copy(favoriteArtists = it.map { artist -> artist.toSearchResultModel() })
                }
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            getFavoriteTracksUseCase.invoke().collect {
                withContext(Dispatchers.Main) {
                    uiState.value =
                        uiState.value.copy(favoriteTracks = it.map { track -> track.toSearchResultModel() })
                }
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            getFavoriteAlbumsUseCase.invoke().collect {
                withContext(Dispatchers.Main) {
                    uiState.value =
                        uiState.value.copy(favoriteAlbums = it.map { album -> album.toSearchResultModel() })
                }
            }
        }
    }

    fun removeFavorite(filter: String, id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            when (filter) {
                "Tracks" -> deleteFavoriteTrackUseCase.invoke(id)
                "Artists" -> deleteFavoriteArtistUseCase.invoke(id)
                "Albums" -> deleteFavoriteAlbumUseCase.invoke(id)
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