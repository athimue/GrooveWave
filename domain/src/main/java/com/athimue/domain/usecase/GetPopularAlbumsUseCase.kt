package com.athimue.domain.usecase

import com.athimue.domain.model.Album
import com.athimue.domain.usecase.definition.SuspendUseCase
import com.athimue.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetPopularAlbumsUseCase : SuspendUseCase<Flow<Resource<List<Album>>>>