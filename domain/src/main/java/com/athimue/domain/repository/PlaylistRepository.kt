package com.athimue.domain.repository

import com.athimue.domain.model.Playlist
import kotlinx.coroutines.flow.Flow

interface PlaylistRepository {

    suspend fun addTrack(playlistId: Int, trackId: Long)

    suspend fun deleteTrack(playlistId: Int, trackId: Long)

    suspend fun addPlaylist(name: String)

    suspend fun deletePlaylist(id: Int)

    suspend fun getPlaylist(playlistId: Int): Flow<Playlist>

    suspend fun getPlaylists(): Flow<List<Playlist>>
}