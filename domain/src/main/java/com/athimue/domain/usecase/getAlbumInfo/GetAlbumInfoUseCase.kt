package com.athimue.domain.usecase.getAlbumInfo

import com.athimue.domain.model.Album
import com.athimue.domain.usecase.SuspendOneInputUseCase
import kotlinx.coroutines.flow.Flow

interface GetAlbumInfoUseCase : SuspendOneInputUseCase<Long, Flow<Result<Album>>>