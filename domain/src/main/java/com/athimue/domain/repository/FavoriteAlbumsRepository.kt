package com.athimue.domain.repository

import com.athimue.domain.model.Album
import kotlinx.coroutines.flow.Flow

interface FavoriteAlbumsRepository {

    suspend fun addFavorite(albumId: Long)

    suspend fun getFavoriteAlbums(): Flow<List<Album>>
}