package com.athimue.usecase.implementation

import com.athimue.domain.model.Album
import com.athimue.domain.repository.PopularRepository
import com.athimue.domain.usecase.GetPopularAlbumsUseCase
import com.athimue.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularAlbumsUseCaseImpl @Inject constructor(
    private val popularRepository: PopularRepository
) : GetPopularAlbumsUseCase {

    override suspend fun invoke(): Flow<Resource<List<Album>>> =
        popularRepository.getPopularAlbums()
}