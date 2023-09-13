package com.athimue.domain.usecase.getpopularalbums

import com.athimue.domain.model.Album
import com.athimue.domain.usecase.SuspendUseCase
import kotlinx.coroutines.flow.Flow

interface GetPopularAlbumsUseCase : SuspendUseCase<Flow<Result<List<Album>>>>