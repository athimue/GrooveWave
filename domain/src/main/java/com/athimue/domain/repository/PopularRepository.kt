package com.athimue.domain.repository

import com.athimue.domain.model.Album
import com.athimue.domain.model.Artist
import com.athimue.domain.model.Track
import kotlinx.coroutines.flow.Flow

interface PopularRepository {

    suspend fun getPopularTracks(): Flow<Result<List<Track>>>

    suspend fun getPopularArtists(): Flow<Result<List<Artist>>>

    suspend fun getPopularAlbums(): Flow<Result<List<Album>>>
}
