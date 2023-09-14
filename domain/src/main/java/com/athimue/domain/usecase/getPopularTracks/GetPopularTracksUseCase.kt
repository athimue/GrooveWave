package com.athimue.domain.usecase.getPopularTracks

import com.athimue.domain.model.Track
import com.athimue.domain.usecase.SuspendUseCase
import kotlinx.coroutines.flow.Flow

interface GetPopularTracksUseCase : SuspendUseCase<Flow<Result<List<Track>>>>
