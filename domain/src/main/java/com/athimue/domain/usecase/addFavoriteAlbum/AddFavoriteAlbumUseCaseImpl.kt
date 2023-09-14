package com.athimue.domain.usecase.addFavoriteAlbum

import com.athimue.domain.repository.FavoriteAlbumsRepository
import javax.inject.Inject

class AddFavoriteAlbumUseCaseImpl @Inject constructor(
    private val favoriteAlbumsRepository: FavoriteAlbumsRepository
) : AddFavoriteAlbumUseCase {

    override suspend fun invoke(input: Long) {
        favoriteAlbumsRepository.addFavorite(input)
    }
}