package com.athimue.domain.repository

import com.athimue.domain.model.Album
import com.athimue.domain.model.Artist
import com.athimue.domain.model.Track
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun getTrack(id: Long): Flow<Result<Track>>

    suspend fun getTracks(query: String): Flow<Result<List<Track>>>

    suspend fun getAlbum(id: Long): Flow<Result<Album>>

    suspend fun getAlbums(query: String): Flow<Result<List<Album>>>

    suspend fun getArtist(id: Long): Flow<Result<Artist>>

    suspend fun getArtists(query: String): Flow<Result<List<Artist>>>

    suspend fun getPlaylists(query: String): Flow<Result<List<Track>>>

    suspend fun getPodcasts(query: String): Flow<Result<List<Track>>>

    suspend fun getRadios(query: String): Flow<Result<List<Track>>>
}