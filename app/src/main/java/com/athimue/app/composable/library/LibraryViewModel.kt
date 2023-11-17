package com.athimue.app.composable.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.usecase.addPlaylist.AddPlaylistUseCase
import com.athimue.domain.usecase.deletePlaylist.DeletePlaylistUseCase
import com.athimue.domain.usecase.getPlaylists.GetPlaylistsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val getPlaylistsUseCase: GetPlaylistsUseCase,
    private val addPlaylistUseCase: AddPlaylistUseCase,
    private val deletePlaylistUseCase: DeletePlaylistUseCase
) : ViewModel() {

    var uiState = MutableStateFlow(LibraryUiState())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getPlaylistsUseCase.invoke().collect {
                withContext(Dispatchers.Main) {
                    uiState.value = uiState.value.copy(playlists = it.map { it.toLibraryUiModel() })
                }
            }
        }
    }

    fun createPlaylist(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            addPlaylistUseCase.invoke(name)
        }
    }

    fun deletePlaylist(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            deletePlaylistUseCase.invoke(id)
        }
    }
}