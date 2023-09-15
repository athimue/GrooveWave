package com.athimue.data.repository

import com.athimue.data.database.dao.ArtistDao
import com.athimue.data.database.entity.ArtistEntity
import com.athimue.data.network.api.DeezerApi
import com.athimue.data.network.dto.artist.toArtist
import com.athimue.domain.model.Artist
import com.athimue.domain.repository.FavoriteArtistsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteArtistsRepositoryImpl @Inject constructor(
    private val favoriteDao: ArtistDao,
    private val deezerApi: DeezerApi
) : FavoriteArtistsRepository {

    override suspend fun addFavorite(artistId: Long) {
        favoriteDao.insert(ArtistEntity(artistId))
    }

    override suspend fun deleteFavorite(artistId: Long) =
        favoriteDao.delete(artistId)

    override suspend fun getFavoriteArtists(): Flow<List<Artist>> =
        favoriteDao.getFavorites().map { artistEntities ->
            artistEntities.mapNotNull { artistEntity ->
                deezerApi.getArtist(artistEntity.id).takeIf { it.isSuccessful }?.body()?.toArtist()
            }
        }
}