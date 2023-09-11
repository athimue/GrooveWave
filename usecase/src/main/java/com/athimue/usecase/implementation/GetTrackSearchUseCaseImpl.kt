package com.athimue.usecase.implementation

import com.athimue.domain.model.Track
import com.athimue.domain.repository.SearchRepository
import com.athimue.domain.usecase.GetTrackSearchUseCase
import com.athimue.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrackSearchUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository
) : GetTrackSearchUseCase {
    override suspend fun invoke(input: String): Flow<Resource<List<Track>>> =
        searchRepository.getTracks(input)
}