package com.athimue.domain.usecase

import com.athimue.domain.model.Track
import com.athimue.domain.usecase.definition.SuspendUseCase
import kotlinx.coroutines.flow.Flow

interface GetFavoriteTracksUseCase : SuspendUseCase<Flow<List<Track>>>