package com.athimue.domain.usecase.getfavoriteartists

import com.athimue.domain.model.Artist
import com.athimue.domain.usecase.SuspendUseCase
import kotlinx.coroutines.flow.Flow

interface GetFavoriteArtistsUseCase : SuspendUseCase<Flow<Result<List<Artist>>>> {
}