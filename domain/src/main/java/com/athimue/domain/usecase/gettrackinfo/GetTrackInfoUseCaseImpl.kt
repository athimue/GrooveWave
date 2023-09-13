package com.athimue.domain.usecase.gettrackinfo

import com.athimue.domain.model.Track
import com.athimue.domain.repository.SearchRepository
import com.athimue.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrackInfoUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository
) : GetTrackInfoUseCase {

    override suspend fun invoke(input: Long): Flow<Resource<Track>> =
        searchRepository.getTrack(input)
}