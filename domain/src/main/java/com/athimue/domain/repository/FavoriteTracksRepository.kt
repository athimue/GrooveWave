package com.athimue.domain.repository

import kotlinx.coroutines.flow.Flow

interface FavoriteTracksRepository {

    suspend fun addFavorite(trackId: Long)

    suspend fun getFavorites(): Flow<List<Long>>

}