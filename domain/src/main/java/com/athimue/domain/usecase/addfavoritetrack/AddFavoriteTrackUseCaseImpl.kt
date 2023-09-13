package com.athimue.domain.usecase.addfavoritetrack

import com.athimue.domain.repository.FavoriteTracksRepository
import javax.inject.Inject

class AddFavoriteTrackUseCaseImpl @Inject constructor(
    private val favoriteTracksRepository: FavoriteTracksRepository
) : AddFavoriteTrackUseCase {

    override suspend fun invoke(input: Long) {
        favoriteTracksRepository.addFavorite(input)
    }
}