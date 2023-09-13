package com.athimue.domain.usecase.getpopularartists

import com.athimue.domain.model.Artist
import com.athimue.domain.usecase.SuspendUseCase
import kotlinx.coroutines.flow.Flow

interface GetPopularArtistsUseCase : SuspendUseCase<Flow<Result<List<Artist>>>>