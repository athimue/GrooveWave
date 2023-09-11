package com.athimue.data.repository

import com.athimue.data.database.dao.PlaylistDao
import com.athimue.data.database.entity.PlaylistEntity
import com.athimue.data.database.entity.TrackEntity
import com.athimue.data.database.entity.toPlaylist
import com.athimue.domain.model.Playlist
import com.athimue.domain.repository.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlaylistRepositoryImpl @Inject constructor(
    private val playlistDao: PlaylistDao
) : PlaylistRepository {

    override suspend fun addTrack(playlistId: Int, trackId: Long) {
        val playlist = playlistDao.getPlaylist(playlistId).first()
        playlistDao.update(
            playlist.copy(
                tracks = listOf(
                    listOf(playlist.tracks).flatten(),
                    listOf(TrackEntity(trackId))
                ).flatten()
            )
        )
    }

    override suspend fun deleteTrack(playlistId: Int, trackId: Long) {
        val playlist = playlistDao.getPlaylist(playlistId).first()
        playlistDao.update(
            playlist.copy(
                tracks = playlist.tracks.filter { it.id != trackId }
            )
        )
    }

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

    override suspend fun getPlaylist(playlistId: Int): Flow<Playlist> =
        playlistDao.getPlaylist(playlistId).map(PlaylistEntity::toPlaylist)

    override suspend fun getPlaylists(): Flow<List<Playlist>> =
        playlistDao.getPlaylists().map { playlists -> playlists.map { it.toPlaylist() } }
}