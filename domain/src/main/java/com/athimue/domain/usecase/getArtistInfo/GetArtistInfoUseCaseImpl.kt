package com.athimue.domain.usecase.getArtistInfo

import com.athimue.domain.model.Artist
import com.athimue.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArtistInfoUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository
) : GetArtistInfoUseCase {

    override suspend fun invoke(input: Long): Flow<Result<Artist>> =
        searchRepository.getArtist(input)
}