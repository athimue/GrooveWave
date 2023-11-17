package com.athimue.app.composable.track

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.usecase.getTrackInfo.GetTrackInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TrackViewModel @Inject constructor(
    private val getTrackInfoUseCase: GetTrackInfoUseCase
) : ViewModel() {

    var uiState: MutableStateFlow<TrackUiState> = MutableStateFlow(TrackUiState())

    fun loadTrack(trackId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                getTrackInfoUseCase.invoke(trackId).collect {
                    it.getOrNull()?.let { track ->
                        uiState.value = uiState.value.copy(track = track.toTrackUiModel())
                    }
                }
            }
        }
    }
}