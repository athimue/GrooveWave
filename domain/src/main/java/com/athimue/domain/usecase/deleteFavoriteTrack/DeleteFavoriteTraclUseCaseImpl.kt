package com.athimue.domain.usecase.deleteFavoriteTrack

import com.athimue.domain.repository.FavoriteTracksRepository
import javax.inject.Inject

class DeleteFavoriteTrackUseCaseImpl @Inject constructor(
    private val tracksRepository: FavoriteTracksRepository
) : DeleteFavoriteTrackUseCase {

    override suspend fun invoke(input: Long) {
        tracksRepository.deleteFavorite(input)
    }
}