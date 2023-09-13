package com.athimue.app.composable.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.model.Track
import com.athimue.domain.repository.PlaylistRepository
import com.athimue.domain.usecase.gettrackinfo.GetTrackInfoUseCase
import com.athimue.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(
    private val playlistRepository: PlaylistRepository,
    private val getTrackInfoUseCase: GetTrackInfoUseCase
) : ViewModel() {

    var uiState = MutableStateFlow(PlaylistUiState())

    fun loadPlaylist(playlistId: Int) {
        viewModelScope.launch {
            playlistRepository.getPlaylist(playlistId).collect { playlist ->
                uiState.value =
                    uiState.value.copy(
                        playlist = PlaylistUiModel(
                            playlist.id,
                            playlist.name,
                            loadPlaylistTracks(playlist.tracks)
                        )
                    )
            }
        }
    }

    fun deletePlaylistTrack(trackId: Long, playlistId: Int) {
        viewModelScope.launch {
            playlistRepository.deleteTrack(playlistId, trackId)
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
}