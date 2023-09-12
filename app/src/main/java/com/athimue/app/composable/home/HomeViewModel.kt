package com.athimue.app.composable.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.model.Album
import com.athimue.domain.model.Artist
import com.athimue.domain.model.Track
import com.athimue.domain.usecase.GetPopularAlbumsUseCase
import com.athimue.domain.usecase.GetPopularArtistsUseCase
import com.athimue.domain.usecase.GetPopularTracksUseCase
import com.athimue.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View model to get a text using [GetPopularTracksUseCase].
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularTracksUseCase: GetPopularTracksUseCase,
    private val getPopularAlbumsUseCase: GetPopularAlbumsUseCase,
    private val getPopularArtistsUseCase: GetPopularArtistsUseCase
) : ViewModel() {

    var uiState = MutableStateFlow(HomeUiState())

    init {
        viewModelScope.launch {
            getPopularTracksUseCase.invoke().collect { result ->
                when (result) {
                    is Resource.Success -> uiState.value =
                        uiState.value.copy(tracks = result.data.map { it.toLazyRowItemModel() })
                    is Resource.Error -> {
                        uiState.value = uiState.value.copy(tracks = emptyList())
                    }
                }
            }
            getPopularAlbumsUseCase.invoke().collect { result ->
                when (result) {
                    is Resource.Success -> uiState.value =
                        uiState.value.copy(albums = result.data.map {
                            it.toLazyRowItemModel()
                        })
                    is Resource.Error -> uiState.value =
                        uiState.value.copy(albums = emptyList())
                }
            }
            getPopularArtistsUseCase.invoke().collect { result ->
                when (result) {
                    is Resource.Success -> uiState.value =
                        uiState.value.copy(artists = result.data.map {
                            it.toLazyRowItemModel()
                        })
                    is Resource.Error -> uiState.value =
                        uiState.value.copy(artists = emptyList())
                }
            }
        }
    }

    private fun Track.toLazyRowItemModel(): LazyRowItemModel = LazyRowItemModel(title, cover)

    private fun Album.toLazyRowItemModel(): LazyRowItemModel = LazyRowItemModel(name, cover)

    private fun Artist.toLazyRowItemModel(): LazyRowItemModel = LazyRowItemModel(name, cover)
}
