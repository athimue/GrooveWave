package com.athimue.app.composable.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.model.Album
import com.athimue.domain.model.Artist
import com.athimue.domain.model.Track
import com.athimue.domain.usecase.getPopularAlbums.GetPopularAlbumsUseCase
import com.athimue.domain.usecase.getPopularArtists.GetPopularArtistsUseCase
import com.athimue.domain.usecase.getPopularTracks.GetPopularTracksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularTracksUseCase: GetPopularTracksUseCase,
    private val getPopularAlbumsUseCase: GetPopularAlbumsUseCase,
    private val getPopularArtistsUseCase: GetPopularArtistsUseCase
) : ViewModel() {

    var uiState = MutableStateFlow(HomeUiState())

    init {
        viewModelScope.launch {
            getPopularTracksUseCase.invoke().collect {
                uiState.value =
                    uiState.value.copy(tracks = it.getOrElse { emptyList() }
                        .map { track -> track.toLazyRowItemModel() })
            }
            getPopularAlbumsUseCase.invoke().collect {
                uiState.value =
                    uiState.value.copy(albums = it.getOrElse { emptyList() }.map { album ->
                        album.toLazyRowItemModel()
                    })
            }
            getPopularArtistsUseCase.invoke().collect {
                uiState.value =
                    uiState.value.copy(artists = it.getOrElse { emptyList() }.map { artist ->
                        artist.toLazyRowItemModel()
                    })
            }
        }
    }

    private fun Track.toLazyRowItemModel(): LazyRowItemModel =
        LazyRowItemModel(id, titleShort, cover)

    private fun Album.toLazyRowItemModel(): LazyRowItemModel =
        LazyRowItemModel(id, name, cover)

    private fun Artist.toLazyRowItemModel(): LazyRowItemModel =
        LazyRowItemModel(id, name, cover ?: "")
}
