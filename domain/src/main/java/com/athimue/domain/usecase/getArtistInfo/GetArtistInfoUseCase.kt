package com.athimue.domain.usecase.getArtistInfo

import com.athimue.domain.model.Artist
import com.athimue.domain.usecase.SuspendOneInputUseCase
import kotlinx.coroutines.flow.Flow

interface GetArtistInfoUseCase : SuspendOneInputUseCase<Long, Flow<Result<Artist>>>