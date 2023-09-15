package com.athimue.domain.usecase.deleteFavoriteAlbum

import com.athimue.domain.repository.FavoriteAlbumsRepository
import javax.inject.Inject

class DeleteFavoriteAlbumUseCaseImpl @Inject constructor(
    private val albumsRepository: FavoriteAlbumsRepository
) : DeleteFavoriteAlbumUseCase {

    override suspend fun invoke(input: Long) {
        albumsRepository.deleteFavorite(input)
    }
}