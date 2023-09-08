package com.athimue.usecase.implementation

import com.athimue.domain.model.Track
import com.athimue.domain.repository.PopularRepository
import com.athimue.domain.usecase.GetPopularTracksUseCaseInputOutput
import com.athimue.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularTracksUseCaseImpl @Inject constructor(
    private val popularRepository: PopularRepository
) : GetPopularTracksUseCaseInputOutput {

    override suspend fun invoke(): Flow<Resource<List<Track>>> =
        popularRepository.getPopularTracks()
}
