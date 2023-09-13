package com.athimue.domain.repository

import com.athimue.domain.model.Track
import kotlinx.coroutines.flow.Flow

interface FavoriteTracksRepository {

    suspend fun addFavorite(trackId: Long)

    suspend fun getFavorites(): Flow<List<Track>>

}