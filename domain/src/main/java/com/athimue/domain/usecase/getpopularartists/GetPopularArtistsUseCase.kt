package com.athimue.domain.usecase.getpopularartists

import com.athimue.domain.model.Artist
import com.athimue.domain.usecase.SuspendUseCase
import com.athimue.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetPopularArtistsUseCase : SuspendUseCase<Flow<Resource<List<Artist>>>>