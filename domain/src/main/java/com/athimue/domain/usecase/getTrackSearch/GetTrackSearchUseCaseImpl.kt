package com.athimue.domain.usecase.getTrackSearch

import com.athimue.domain.model.Track
import com.athimue.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrackSearchUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository
) : GetTrackSearchUseCase {

    override suspend fun invoke(input: String): Flow<Result<List<Track>>> =
        searchRepository.getTracks(input)
}