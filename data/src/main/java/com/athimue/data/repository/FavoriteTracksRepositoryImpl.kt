package com.athimue.data.repository

import com.athimue.data.database.dao.TrackDao
import com.athimue.data.database.entity.TrackEntity
import com.athimue.data.network.api.DeezerApi
import com.athimue.data.network.dto.track.toTrack
import com.athimue.domain.model.Track
import com.athimue.domain.repository.FavoriteTracksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteTracksRepositoryImpl @Inject constructor(
    private val trackDao: TrackDao,
    private val deezerApi: DeezerApi
) : FavoriteTracksRepository {

    override suspend fun addFavorite(trackId: Long) {
        trackDao.insert(TrackEntity(trackId))
    }

    override suspend fun deleteFavorite(trackId: Long) {
        trackDao.delete(trackId)
    }

    override suspend fun getFavorites(): Flow<List<Track>> =
        trackDao.getFavorites().map { trackEntities ->
            trackEntities.mapNotNull { trackEntity ->
                deezerApi.getTrack(trackEntity.id).takeIf { it.isSuccessful }?.body()?.toTrack()
            }
        }
}