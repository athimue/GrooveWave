package com.athimue.usecase.implementation

import com.athimue.domain.model.Artist
import com.athimue.domain.repository.SearchRepository
import com.athimue.domain.usecase.GetArtistSearchUseCase
import com.athimue.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArtistSearchUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository
) : GetArtistSearchUseCase {

    override suspend fun invoke(input: String): Flow<Resource<List<Artist>>> =
        searchRepository.getArtists(input)
}