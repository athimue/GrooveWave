package com.athimue.domain.usecase.getPopularTracks

import com.athimue.domain.model.Track
import com.athimue.domain.repository.PopularRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularTracksUseCaseImpl @Inject constructor(
    private val popularRepository: PopularRepository
) : GetPopularTracksUseCase {

    override suspend fun invoke(): Flow<Result<List<Track>>> =
        popularRepository.getPopularTracks()
}
