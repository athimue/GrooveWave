package com.athimue.usecase.implementation

import com.athimue.domain.repository.FavoriteTracksRepository
import com.athimue.domain.usecase.AddFavoriteTrackUseCase
import javax.inject.Inject

class AddFavoriteTrackUseCaseImpl @Inject constructor(
    private val favoriteTracksRepository: FavoriteTracksRepository
) : AddFavoriteTrackUseCase {

    override suspend fun invoke(input: Long) {
        favoriteTracksRepository.addFavorite(input)
    }
}