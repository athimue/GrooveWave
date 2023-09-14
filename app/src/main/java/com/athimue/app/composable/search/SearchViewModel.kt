package com.athimue.app.composable.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.app.composable.playlist.PlaylistUiModel
import com.athimue.domain.model.Album
import com.athimue.domain.model.Artist
import com.athimue.domain.model.Track
import com.athimue.domain.usecase.SuspendOneInputUseCase
import com.athimue.domain.usecase.addFavoriteAlbum.AddFavoriteAlbumUseCase
import com.athimue.domain.usecase.addFavoriteArtist.AddFavoriteArtistUseCase
import com.athimue.domain.usecase.addFavoriteTrack.AddFavoriteTrackUseCase
import com.athimue.domain.usecase.addPlaylistTrack.AddPlaylistTrackUseCase
import com.athimue.domain.usecase.getAlbumSearch.GetAlbumSearchUseCase
import com.athimue.domain.usecase.getArtistSearch.GetArtistSearchUseCase
import com.athimue.domain.usecase.getPlaylists.GetPlaylistsUseCase
import com.athimue.domain.usecase.getTrackSearch.GetTrackSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getTrackSearchUseCase: GetTrackSearchUseCase,
    private val getAlbumSearchUseCase: GetAlbumSearchUseCase,
    private val getArtistSearchUseCase: GetArtistSearchUseCase,
    private val getPlaylistsUseCase: GetPlaylistsUseCase,
    private val addPlaylistTrackUseCase: AddPlaylistTrackUseCase,
    private val addFavoriteTrackUseCase: AddFavoriteTrackUseCase,
    private val addFavoriteArtistUseCase: AddFavoriteArtistUseCase,
    private val addFavoriteAlbumUseCase: AddFavoriteAlbumUseCase,
) : ViewModel() {

    var uiState: MutableStateFlow<SearchUiState> =
        MutableStateFlow(SearchUiState())

    private var searchJob: Job? = null

    init {
        viewModelScope.launch {
            getPlaylistsUseCase.invoke().collect {
                uiState.value = uiState.value.copy(playlists = it.map { playlist ->
                    PlaylistUiModel(
                        id = playlist.id,
                        name = playlist.name,
                        tracks = playlist.tracks
                    )
                })
            }
        }
    }

    fun searchRequested(filter: String, query: String) {
        uiState.value = uiState.value.copy(isLoading = true)
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            if (query.isNotBlank()) {
                when (filter) {
                    "Track" -> searchByQuery(
                        getSearchUseCase = getTrackSearchUseCase,
                        mapper = { it.toSearchResultModel() },
                        query = query
                    )
                    "Album" -> searchByQuery(
                        getAlbumSearchUseCase, { it.toSearchResultModel() }, query
                    )
                    "Artist" -> searchByQuery(
                        getArtistSearchUseCase, mapper = { it.toSearchResultModel() }, query
                    )
                    "Playlist" -> searchByQuery(
                        getTrackSearchUseCase, mapper = { it.toSearchResultModel() }, query
                    )
                    "Podcast" -> searchByQuery(
                        getTrackSearchUseCase, mapper = { it.toSearchResultModel() }, query
                    )
                    "Radio" -> searchByQuery(
                        getTrackSearchUseCase, mapper = { it.toSearchResultModel() }, query
                    )
                }
            }
        }
    }

    fun addTrackToPlaylist(playlistId: Int, trackId: Long) {
        viewModelScope.launch {
            addPlaylistTrackUseCase.invoke(
                firstInput = playlistId,
                secondInput = trackId
            )
        }
    }

    fun addFavorite(filterSelected: String, id: Long) {
        viewModelScope.launch {
            when (filterSelected) {
                "Track" -> addFavoriteTrackUseCase.invoke(id)
                "Album" -> addFavoriteAlbumUseCase.invoke(id)
                "Artist" -> addFavoriteArtistUseCase.invoke(id)
            }
        }
    }

    private suspend fun <T> searchByQuery(
        getSearchUseCase: SuspendOneInputUseCase<String, Flow<Result<List<T>>>>,
        mapper: (T) -> SearchResultModel,
        query: String
    ) {
        getSearchUseCase.invoke(query).collect {
            uiState.value = uiState.value.copy(
                isLoading = false,
                searchResult = it.getOrElse { emptyList() }.map { result -> mapper(result) })
        }
    }

    private fun Track.toSearchResultModel(): SearchResultModel = SearchResultModel(
        id = id,
        title = title,
        subTitle = artist.name,
        picture = cover,
    )

    private fun Album.toSearchResultModel(): SearchResultModel = SearchResultModel(
        id = id,
        title = name,
        subTitle = "Tracks : $nbTracks",
        picture = cover,
    )

    private fun Artist.toSearchResultModel(): SearchResultModel = SearchResultModel(
        id = id,
        title = name,
        subTitle = "Albums : $nbAlbum",
        picture = cover,
    )
}