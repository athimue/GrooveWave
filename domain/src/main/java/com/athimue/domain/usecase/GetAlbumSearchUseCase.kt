package com.athimue.domain.usecase

import com.athimue.domain.model.Album
import com.athimue.domain.usecase.definition.SuspendWithInputUseCase
import com.athimue.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetAlbumSearchUseCase :
    SuspendWithInputUseCase<String, Flow<Resource<List<Album>>>>