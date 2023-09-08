package com.athimue.data.repository

import com.athimue.data.database.dao.PlaylistDao
import com.athimue.data.database.entity.PlaylistEntity
import com.athimue.data.database.entity.toPlaylist
import com.athimue.domain.model.Playlist
import com.athimue.domain.repository.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlaylistRepositoryImpl @Inject constructor(
    private val playlistDao: PlaylistDao
) : PlaylistRepository {

    override suspend fun addPlaylist(name: String) {
        playlistDao.insert(
            playlistEntity = PlaylistEntity(
                name = name,
                tracks = emptyList()
            )
        )
    }

    override suspend fun deletePlaylist(id: Int) {
        playlistDao.delete(id)
    }

    override suspend fun getPlaylists(): Flow<List<Playlist>> =
        playlistDao.getPlaylists().map { playlists -> playlists.map { it.toPlaylist() } }
}