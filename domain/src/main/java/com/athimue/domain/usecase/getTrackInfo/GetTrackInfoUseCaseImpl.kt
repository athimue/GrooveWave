package com.athimue.domain.usecase.getTrackInfo

import com.athimue.domain.model.Track
import com.athimue.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrackInfoUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository
) : GetTrackInfoUseCase {

    override suspend fun invoke(input: Long): Flow<Result<Track>> =
        searchRepository.getTrack(input)
}