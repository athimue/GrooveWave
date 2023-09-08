package com.athimue.domain.usecase

import com.athimue.domain.model.Artist
import com.athimue.domain.usecase.definition.SuspendWithInputUseCase
import com.athimue.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetArtistSearchUseCase :
    SuspendWithInputUseCase<String, Flow<Resource<List<Artist>>>>