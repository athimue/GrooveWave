package com.athimue.data.repository

import com.athimue.data.database.dao.TrackDao
import com.athimue.data.database.entity.TrackEntity
import com.athimue.domain.repository.FavoriteTracksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteTracksRepositoryImpl @Inject constructor(
    private val trackDao: TrackDao
) : FavoriteTracksRepository {

    override suspend fun addFavorite(trackId: Long) {
        trackDao.insert(TrackEntity(trackId))
    }

    override suspend fun getFavorites(): Flow<List<Long>> =
        trackDao.getFavorites().map { it.map { track -> track.id } }
}