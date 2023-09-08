package com.athimue.domain.repository

import com.athimue.domain.model.Playlist
import kotlinx.coroutines.flow.Flow

interface PlaylistRepository {

    suspend fun addPlaylist(name: String)

    suspend fun deletePlaylist(id: Int)

    suspend fun getPlaylists(): Flow<List<Playlist>>
}