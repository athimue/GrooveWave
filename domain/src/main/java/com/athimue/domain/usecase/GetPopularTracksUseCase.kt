package com.athimue.domain.usecase

import com.athimue.domain.model.Track
import com.athimue.domain.usecase.definition.SuspendUseCase
import com.athimue.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetPopularTracksUseCase : SuspendUseCase<Flow<Resource<List<Track>>>>
