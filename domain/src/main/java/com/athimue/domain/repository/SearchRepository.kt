package com.athimue.domain.repository

import com.athimue.domain.model.Album
import com.athimue.domain.model.Artist
import com.athimue.domain.model.Track
import com.athimue.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun getTrack(id: Long): Flow<Resource<Track>>

    suspend fun getTracks(query: String): Flow<Resource<List<Track>>>

    suspend fun getAlbums(query: String): Flow<Resource<List<Album>>>

    suspend fun getArtists(query: String): Flow<Resource<List<Artist>>>

    suspend fun getPlaylists(query: String): Flow<Resource<List<Track>>>

    suspend fun getPodcasts(query: String): Flow<Resource<List<Track>>>

    suspend fun getRadios(query: String): Flow<Resource<List<Track>>>
}