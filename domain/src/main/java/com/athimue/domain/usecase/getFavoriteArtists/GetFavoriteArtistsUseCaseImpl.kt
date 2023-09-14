package com.athimue.domain.usecase.getFavoriteArtists

import com.athimue.domain.model.Artist
import com.athimue.domain.repository.FavoriteArtistsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteArtistsUseCaseImpl @Inject constructor(
    private val favoriteArtistsRepository: FavoriteArtistsRepository
) : GetFavoriteArtistsUseCase {

    override suspend fun invoke(): Flow<List<Artist>> =
        favoriteArtistsRepository.getFavoriteArtists()

}