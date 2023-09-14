package com.athimue.domain.usecase.getTrackSearch

import com.athimue.domain.model.Track
import com.athimue.domain.usecase.SuspendOneInputUseCase
import kotlinx.coroutines.flow.Flow

interface GetTrackSearchUseCase :
    SuspendOneInputUseCase<String, Flow<Result<List<Track>>>>