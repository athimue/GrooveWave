package com.athimue.domain.repository

import com.athimue.domain.model.Artist
import kotlinx.coroutines.flow.Flow

interface FavoriteArtistsRepository {

    suspend fun addFavorite(artistId: Long)

    suspend fun deleteFavorite(artistId: Long)

    suspend fun getFavoriteArtists(): Flow<List<Artist>>
}