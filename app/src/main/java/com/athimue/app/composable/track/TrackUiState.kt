package com.athimue.app.composable.track

import com.athimue.domain.model.Track

data class TrackUiState(
    var isLoading: Boolean = false,
    var track: Track? = null
)