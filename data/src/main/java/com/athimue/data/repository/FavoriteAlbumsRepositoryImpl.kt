package com.athimue.data.repository

import com.athimue.data.database.dao.AlbumDao
import com.athimue.data.database.entity.AlbumEntity
import com.athimue.data.network.api.DeezerApi
import com.athimue.data.network.dto.album.toAlbum
import com.athimue.domain.model.Album
import com.athimue.domain.repository.FavoriteAlbumsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteAlbumsRepositoryImpl @Inject constructor(
    private val albumDao: AlbumDao,
    private val deezerApi: DeezerApi
) : FavoriteAlbumsRepository {

    override suspend fun addFavorite(albumId: Long) {
        albumDao.insert(AlbumEntity(albumId))
    }

    override suspend fun getFavoriteAlbums(): Flow<List<Album>> =
        albumDao.getFavorites().map { albumEntities ->
            albumEntities.mapNotNull { albumEntity ->
                deezerApi.getAlbum(albumEntity.id).takeIf { it.isSuccessful }?.body()?.toAlbum()
            }
        }
}