package com.athimue.domain.usecase.getfavoriteartists

import com.athimue.domain.model.Artist
import com.athimue.domain.repository.FavoriteArtistsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteArtistsUseCaseImpl @Inject constructor(
    private val favoriteArtistsRepository: FavoriteArtistsRepository
) : GetFavoriteArtistsUseCase {

    override suspend fun invoke(): Flow<Result<List<Artist>>> {
        TODO("Not yet implemented")
    }

}