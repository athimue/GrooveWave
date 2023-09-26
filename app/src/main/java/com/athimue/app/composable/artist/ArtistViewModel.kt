package com.athimue.app.composable.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.usecase.getArtistInfo.GetArtistInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ArtistViewModel @Inject constructor(
    private val getArtistInfoUseCase: GetArtistInfoUseCase
) : ViewModel() {

    var uiState: MutableStateFlow<ArtistUiState> =
        MutableStateFlow(ArtistUiState())

    fun loadArtist(artistId: Long) {
        viewModelScope.launch {
            getArtistInfoUseCase.invoke(artistId).collect {
                it.getOrNull()
                    ?.let { artist ->
                        uiState.value = uiState.value.copy(artist = artist.toArtistUiModel())
                    }
            }
        }
    }
}