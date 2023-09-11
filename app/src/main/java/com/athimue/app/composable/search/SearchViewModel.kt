package com.athimue.app.composable.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.app.composable.playlist.PlaylistUiModel
import com.athimue.domain.model.Album
import com.athimue.domain.model.Artist
import com.athimue.domain.model.Track
import com.athimue.domain.repository.PlaylistRepository
import com.athimue.domain.usecase.*
import com.athimue.domain.usecase.definition.SuspendWithInputUseCase
import com.athimue.domain.util.Resource
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
    private val getTrackInfoUseCase: GetTrackInfoUseCase,
    private val getPlaylistUseCase: GetPlaylistUseCase,
    private val playlistRepository: PlaylistRepository
) : ViewModel() {

    var uiState: MutableStateFlow<SearchUiState> =
        MutableStateFlow(SearchUiState())

    private var searchJob: Job? = null

    init {
        viewModelScope.launch {
            getPlaylistUseCase.invoke().collect {
                uiState.value = uiState.value.copy(playlists = it.map { playlist ->
                    Log.d("COUCOU", playlist.toString())
                    PlaylistUiModel(
                        id = playlist.id,
                        name = playlist.name,
                        tracks = loadPlaylistTracks(playlist.tracks)
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
            playlistRepository.addTrack(
                playlistId = playlistId,
                trackId = trackId
            )
        }
    }

    private suspend fun loadPlaylistTracks(ids: List<Long>): List<Track> {
        val tracks = mutableListOf<Track>()
        ids.forEach { trackId ->
            getTrackInfoUseCase.invoke(trackId).collect {
                when (it) {
                    is Resource.Success ->
                        tracks.add(it.data)
                    else -> {}
                }
            }
        }
        return tracks
    }

    private suspend fun <T> searchByQuery(
        getSearchUseCase: SuspendWithInputUseCase<String, Flow<Resource<List<T>>>>,
        mapper: (T) -> SearchResultModel,
        query: String
    ) {
        getSearchUseCase.invoke(query).collect { result ->
            when (result) {
                is Resource.Success -> {
                    uiState.value = uiState.value.copy(
                        isLoading = false,
                        searchResult = result.data.map { mapper(it) })
                }
                is Resource.Error -> {
                    uiState.value =
                        uiState.value.copy(isLoading = false, searchResult = emptyList())
                }
            }
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
        subTitle = id.toString(),
        picture = cover,
    )

    private fun Artist.toSearchResultModel(): SearchResultModel = SearchResultModel(
        id = id,
        title = name,
        subTitle = link,
        picture = cover,
    )
}