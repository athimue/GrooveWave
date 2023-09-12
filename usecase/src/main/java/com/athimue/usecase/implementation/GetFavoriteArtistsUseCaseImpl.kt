package com.athimue.usecase.implementation

import com.athimue.domain.model.Artist
import com.athimue.domain.repository.FavoriteArtistsRepository
import com.athimue.domain.usecase.GetFavoriteArtistsUseCase
import com.athimue.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteArtistsUseCaseImpl @Inject constructor(
    private val favoriteArtistsRepository: FavoriteArtistsRepository
) : GetFavoriteArtistsUseCase {

    override suspend fun invoke(): Flow<Resource<List<Artist>>> {
        TODO("Not yet implemented")
    }

}