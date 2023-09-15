package com.athimue.app.composable.playlist

import android.media.AudioAttributes
import android.media.MediaPlayer
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
    private val mediaPlayer = MediaPlayer().also {
        it.setAudioAttributes(
            AudioAttributes
                .Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )
    }

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

    fun playTrack(trackUrl: String) {
        mediaPlayer.reset()
        mediaPlayer.setDataSource(trackUrl)
        mediaPlayer.prepare()
        mediaPlayer.start()
    }
}