package com.athimue.usecase.implementation

import com.athimue.domain.model.Album
import com.athimue.domain.repository.FavoriteAlbumsRepository
import com.athimue.domain.usecase.GetFavoriteAlbumsUseCase
import com.athimue.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteAlbumsUseCaseImpl @Inject constructor(
    private val favoriteAlbumsRepository: FavoriteAlbumsRepository
) : GetFavoriteAlbumsUseCase {

    override suspend fun invoke(): Flow<Resource<List<Album>>> {
        TODO("Not yet implemented")
    }

}