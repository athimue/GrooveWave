package com.athimue.domain.usecase.getfavoritealbums

import com.athimue.domain.model.Album
import com.athimue.domain.usecase.SuspendUseCase
import com.athimue.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetFavoriteAlbumsUseCase : SuspendUseCase<Flow<Resource<List<Album>>>> {
}