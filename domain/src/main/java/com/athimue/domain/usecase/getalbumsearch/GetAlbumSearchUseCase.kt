package com.athimue.domain.usecase.getalbumsearch

import com.athimue.domain.model.Album
import com.athimue.domain.usecase.SuspendOneInputUseCase
import kotlinx.coroutines.flow.Flow

interface GetAlbumSearchUseCase :
    SuspendOneInputUseCase<String, Flow<Result<List<Album>>>>