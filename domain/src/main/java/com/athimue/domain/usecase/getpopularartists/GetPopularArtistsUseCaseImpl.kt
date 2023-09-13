package com.athimue.domain.usecase.getpopularartists

import com.athimue.domain.model.Artist
import com.athimue.domain.repository.PopularRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularArtistsUseCaseImpl @Inject constructor(
    private val popularRepository: PopularRepository
) : GetPopularArtistsUseCase {

    override suspend fun invoke(): Flow<Result<List<Artist>>> =
        popularRepository.getPopularArtists()

}