package com.athimue.domain.usecase.getFavoriteAlbums

import com.athimue.domain.model.Album
import com.athimue.domain.usecase.SuspendUseCase
import kotlinx.coroutines.flow.Flow

interface GetFavoriteAlbumsUseCase : SuspendUseCase<Flow<List<Album>>>