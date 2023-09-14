package com.athimue.app.composable.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.usecase.getAlbumInfo.GetAlbumInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val getAlbumInfoUseCase: GetAlbumInfoUseCase
) : ViewModel() {

    var uiState: MutableStateFlow<AlbumUiState> =
        MutableStateFlow(AlbumUiState())

    fun loadAlbum(albumId: Long) {
        viewModelScope.launch {
            getAlbumInfoUseCase.invoke(albumId).collect {
                it.getOrNull()?.let { album -> uiState.value = uiState.value.copy(album = album) }
            }
        }
    }
}