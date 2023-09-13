package com.athimue.domain.usecase.getfavoriteartists

import com.athimue.domain.model.Artist
import com.athimue.domain.usecase.SuspendUseCase
import com.athimue.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetFavoriteArtistsUseCase : SuspendUseCase<Flow<Resource<List<Artist>>>> {
}