package com.athimue.app.composable.track

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.usecase.gettrackinfo.GetTrackInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackViewModel @Inject constructor(
    private val getTrackInfoUseCase: GetTrackInfoUseCase
) : ViewModel() {

    var uiState: MutableStateFlow<TrackUiState> =
        MutableStateFlow(TrackUiState())

    fun loadTrack(trackId: Long) {
        viewModelScope.launch {
            getTrackInfoUseCase.invoke(trackId).collect {
                it.getOrNull()?.let { track -> uiState.value = uiState.value.copy(track = track) }
            }
        }
    }
}