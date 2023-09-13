package com.athimue.domain.usecase.gettrackinfo

import com.athimue.domain.model.Track
import com.athimue.domain.usecase.SuspendWithInputUseCase
import com.athimue.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetTrackInfoUseCase :
    SuspendWithInputUseCase<Long, Flow<Resource<Track>>>