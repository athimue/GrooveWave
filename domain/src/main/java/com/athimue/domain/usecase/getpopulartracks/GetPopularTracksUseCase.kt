package com.athimue.domain.usecase.getpopulartracks

import com.athimue.domain.model.Track
import com.athimue.domain.usecase.SuspendUseCase
import com.athimue.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetPopularTracksUseCase : SuspendUseCase<Flow<Resource<List<Track>>>>
