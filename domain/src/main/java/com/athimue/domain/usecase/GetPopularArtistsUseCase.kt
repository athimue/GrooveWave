package com.athimue.domain.usecase

import com.athimue.domain.model.Artist
import com.athimue.domain.usecase.definition.SuspendUseCase
import com.athimue.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetPopularArtistsUseCase : SuspendUseCase<Flow<Resource<List<Artist>>>>