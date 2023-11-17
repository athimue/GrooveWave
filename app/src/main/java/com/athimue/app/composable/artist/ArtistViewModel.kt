package com.athimue.app.composable.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.usecase.getArtistInfo.GetArtistInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class ArtistViewModel @Inject constructor(
    private val getArtistInfoUseCase: GetArtistInfoUseCase
) : ViewModel() {

    var uiState: MutableStateFlow<ArtistUiState> = MutableStateFlow(ArtistUiState())

    fun loadArtist(artistId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            getArtistInfoUseCase.invoke(artistId).collect {
                it.getOrNull()?.let { artist ->
                    withContext(Dispatchers.Main) {
                        uiState.value = uiState.value.copy(artist = artist.toArtistUiModel())
                    }
                }
            }
        }
    }
}