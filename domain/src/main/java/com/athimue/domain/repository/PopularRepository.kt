package com.athimue.domain.repository

import com.athimue.domain.model.Album
import com.athimue.domain.model.Artist
import com.athimue.domain.model.Track
import com.athimue.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface PopularRepository {

    suspend fun getPopularTracks(): Flow<Resource<List<Track>>>

    suspend fun getPopularArtists(): Flow<Resource<List<Artist>>>

    suspend fun getPopularAlbums(): Flow<Resource<List<Album>>>
}
