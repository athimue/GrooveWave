package com.athimue.domain.usecase.getfavoritetracks

import com.athimue.domain.model.Track
import com.athimue.domain.repository.FavoriteTracksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteTracksUseCaseImpl @Inject constructor(
    private val favoriteTracksRepository: FavoriteTracksRepository,
) : GetFavoriteTracksUseCase {

    override suspend fun invoke(): Flow<List<Track>> =
        favoriteTracksRepository.getFavorites()
}