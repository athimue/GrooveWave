package com.athimue.domain.usecase.gettrackinfo

import com.athimue.domain.model.Track
import com.athimue.domain.usecase.SuspendOneInputUseCase
import kotlinx.coroutines.flow.Flow

interface GetTrackInfoUseCase :
    SuspendOneInputUseCase<Long, Flow<Result<Track>>>