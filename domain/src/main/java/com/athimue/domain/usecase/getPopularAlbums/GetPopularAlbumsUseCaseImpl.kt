package com.athimue.domain.usecase.getPopularAlbums

import com.athimue.domain.model.Album
import com.athimue.domain.repository.PopularRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularAlbumsUseCaseImpl @Inject constructor(
    private val popularRepository: PopularRepository
) : GetPopularAlbumsUseCase {

    override suspend fun invoke(): Flow<Result<List<Album>>> =
        popularRepository.getPopularAlbums()
}