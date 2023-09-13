package com.athimue.app.composable.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.repository.PlaylistRepository
import com.athimue.domain.usecase.getplaylists.GetPlaylistsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val playlistRepository: PlaylistRepository,
    private val getPlaylistsUseCase: GetPlaylistsUseCase
) : ViewModel() {

    var uiState = MutableStateFlow(LibraryUiState())

    init {
        viewModelScope.launch {
            getPlaylistsUseCase.invoke().collect {
                uiState.value = uiState.value.copy(playlists = it)
            }
        }
    }

    fun createPlaylist(name: String) {
        viewModelScope.launch {
            playlistRepository.addPlaylist(name)
        }
    }

    fun deletePlaylist(id: Int) {
        viewModelScope.launch {
            playlistRepository.deletePlaylist(id)
        }
    }
}