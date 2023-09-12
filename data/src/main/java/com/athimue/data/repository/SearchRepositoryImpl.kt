package com.athimue.data.repository

import com.athimue.data.network.api.DeezerApi
import com.athimue.data.network.dto.album.AlbumDto
import com.athimue.data.network.dto.album.toAlbum
import com.athimue.data.network.dto.artist.ArtistDto
import com.athimue.data.network.dto.artist.toArtist
import com.athimue.data.network.dto.track.TrackDto
import com.athimue.data.network.dto.track.toTrack
import com.athimue.domain.model.Album
import com.athimue.domain.model.Artist
import com.athimue.domain.model.Track
import com.athimue.domain.repository.SearchRepository
import com.athimue.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val deezerApi: DeezerApi
) : SearchRepository {

    override suspend fun getTrack(id: Long): Flow<Resource<Track>> {
        return flow {
            runCatching {
                val response = deezerApi.getTrack(id)
                response.takeIf { it.isSuccessful }?.body()?.let {
                    emit(Resource.Success(it.toTrack()))
                } ?: emit(Resource.Error("No data"))
            }.getOrElse {
                emit(Resource.Error(it.toString()))
            }
        }
    }

    override suspend fun getTracks(query: String): Flow<Resource<List<Track>>> {
        return flow {
            runCatching {
                val response = deezerApi.getSearchedTracks(query)
                response.takeIf { it.isSuccessful }?.body()?.let {
                    emit(Resource.Success(it.data.map(TrackDto::toTrack)))
                } ?: emit(Resource.Error("No data"))
            }.getOrElse {
                emit(Resource.Error(it.toString()))
            }
        }
    }

    override suspend fun getAlbums(query: String): Flow<Resource<List<Album>>> {
        return flow {
            runCatching {
                val response = deezerApi.getSearchedAlbums(query)
                response.takeIf { it.isSuccessful }?.body()?.let {
                    emit(Resource.Success(it.data.map(AlbumDto::toAlbum)))
                } ?: emit(Resource.Error("No data"))
            }.getOrElse {
                emit(Resource.Error(it.toString()))
            }
        }
    }

    override suspend fun getArtists(query: String): Flow<Resource<List<Artist>>> {
        return flow {
            runCatching {
                val response = deezerApi.getSearchedArtists(query)
                response.takeIf { it.isSuccessful }?.body()?.let {
                    emit(Resource.Success(it.data.map(ArtistDto::toArtist)))
                } ?: emit(Resource.Error("No data"))
            }.getOrElse {
                emit(Resource.Error(it.toString()))
            }
        }
    }

    override suspend fun getPlaylists(query: String): Flow<Resource<List<Track>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPodcasts(query: String): Flow<Resource<List<Track>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRadios(query: String): Flow<Resource<List<Track>>> {
        TODO("Not yet implemented")
    }


}