package com.athimue.domain.usecase.addFavoriteArtist

import com.athimue.domain.repository.FavoriteArtistsRepository
import javax.inject.Inject

class AddFavoriteArtistUseCaseImpl @Inject constructor(
    private val favoriteArtistsRepository: FavoriteArtistsRepository
) : AddFavoriteArtistUseCase {

    override suspend fun invoke(input: Long) {
        favoriteArtistsRepository.addFavorite(input)
    }

}