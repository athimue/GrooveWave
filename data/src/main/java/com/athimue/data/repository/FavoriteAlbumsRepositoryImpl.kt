package com.athimue.data.repository

import com.athimue.domain.model.Album
import com.athimue.domain.repository.FavoriteAlbumsRepository
import javax.inject.Inject

class FavoriteAlbumsRepositoryImpl @Inject constructor() : FavoriteAlbumsRepository {

    override suspend fun getFavoriteAlbums(): List<Album> {
        TODO("Not yet implemented")
    }
}