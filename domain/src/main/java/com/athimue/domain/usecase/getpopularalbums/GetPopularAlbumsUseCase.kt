package com.athimue.domain.usecase.getpopularalbums

import com.athimue.domain.model.Album
import com.athimue.domain.usecase.SuspendUseCase
import com.athimue.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetPopularAlbumsUseCase : SuspendUseCase<Flow<Resource<List<Album>>>>