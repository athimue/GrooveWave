package com.athimue.domain.usecase.deleteFavoriteArtist

import com.athimue.domain.repository.FavoriteArtistsRepository
import javax.inject.Inject

class DeleteFavoriteArtistUseCaseImpl @Inject constructor(
    private val artistsRepository: FavoriteArtistsRepository
) : DeleteFavoriteArtistUseCase {

    override suspend fun invoke(input: Long) {
        artistsRepository.deleteFavorite(input)
    }
}