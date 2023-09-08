package com.athimue.usecase.implementation

import com.athimue.domain.model.Artist
import com.athimue.domain.repository.PopularRepository
import com.athimue.domain.usecase.GetPopularArtistsUseCase
import com.athimue.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularArtistsUseCaseImpl @Inject constructor(
    private val popularRepository: PopularRepository
) : GetPopularArtistsUseCase {

    override suspend fun invoke(): Flow<Resource<List<Artist>>> =
        popularRepository.getPopularArtists()

}