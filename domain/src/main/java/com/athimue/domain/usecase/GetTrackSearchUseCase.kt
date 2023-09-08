package com.athimue.domain.usecase

import com.athimue.domain.model.Track
import com.athimue.domain.usecase.definition.SuspendWithInputUseCase
import com.athimue.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetTrackSearchUseCase :
    SuspendWithInputUseCase<String, Flow<Resource<List<Track>>>>