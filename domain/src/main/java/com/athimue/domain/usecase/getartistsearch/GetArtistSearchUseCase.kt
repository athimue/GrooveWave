package com.athimue.domain.usecase.getartistsearch

import com.athimue.domain.model.Artist
import com.athimue.domain.usecase.SuspendOneInputUseCase
import kotlinx.coroutines.flow.Flow

interface GetArtistSearchUseCase :
    SuspendOneInputUseCase<String, Flow<Result<List<Artist>>>>