package com.athimue.domain.usecase.getFavoriteAlbums

import com.athimue.domain.model.Album
import com.athimue.domain.repository.FavoriteAlbumsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteAlbumsUseCaseImpl @Inject constructor(
    private val favoriteAlbumsRepository: FavoriteAlbumsRepository
) : GetFavoriteAlbumsUseCase {

    override suspend fun invoke(): Flow<List<Album>> =
        favoriteAlbumsRepository.getFavoriteAlbums()

}