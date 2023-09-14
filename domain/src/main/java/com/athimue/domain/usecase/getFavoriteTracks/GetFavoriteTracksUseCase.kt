package com.athimue.domain.usecase.getFavoriteTracks

import com.athimue.domain.model.Track
import com.athimue.domain.usecase.SuspendUseCase
import kotlinx.coroutines.flow.Flow

interface GetFavoriteTracksUseCase : SuspendUseCase<Flow<List<Track>>>