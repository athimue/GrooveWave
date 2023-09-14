package com.athimue.app.composable.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.usecase.deletePlaylistTrack.DeletePlaylistTrackUseCase
import com.athimue.domain.usecase.getPlaylistInfo.GetPlaylistInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(
    private val getPlaylistInfoUseCase: GetPlaylistInfoUseCase,
    private val deletePlaylistTrackUseCase: DeletePlaylistTrackUseCase
) : ViewModel() {

    var uiState = MutableStateFlow(PlaylistUiState())

    fun loadPlaylist(playlistId: Int) {
        viewModelScope.launch {
            getPlaylistInfoUseCase.invoke(playlistId).collect { playlist ->
                uiState.value =
                    uiState.value.copy(
                        playlist = PlaylistUiModel(
                            playlist.id,
                            playlist.name,
                            playlist.tracks
                        )
                    )
            }
        }
    }

    fun deletePlaylistTrack(trackId: Long, playlistId: Int) {
        viewModelScope.launch {
            deletePlaylistTrackUseCase.invoke(playlistId, trackId)
        }
    }
}