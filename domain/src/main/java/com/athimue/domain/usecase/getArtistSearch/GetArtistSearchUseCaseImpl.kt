package com.athimue.domain.usecase.getArtistSearch

import com.athimue.domain.model.Artist
import com.athimue.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArtistSearchUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository
) : GetArtistSearchUseCase {

    override suspend fun invoke(input: String): Flow<Result<List<Artist>>> =
        searchRepository.getArtists(input)
}