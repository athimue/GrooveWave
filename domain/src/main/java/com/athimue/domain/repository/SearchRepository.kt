package com.athimue.domain.repository

import com.athimue.domain.model.Album
import com.athimue.domain.model.Artist
import com.athimue.domain.model.Track
import com.athimue.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun getTrackSearch(query: String): Flow<Resource<List<Track>>>

    suspend fun getAlbumSearch(query: String): Flow<Resource<List<Album>>>

    suspend fun getArtistSearch(query: String): Flow<Resource<List<Artist>>>

    suspend fun getPlaylistSearch(query: String): Flow<Resource<List<Track>>>

    suspend fun getPodcastSearch(query: String): Flow<Resource<List<Track>>>

    suspend fun getRadioSearch(query: String): Flow<Resource<List<Track>>>
}