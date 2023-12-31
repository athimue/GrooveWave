package com.athimue.data.repository

import com.athimue.data.network.api.DeezerApi
import com.athimue.data.network.dto.album.toAlbum
import com.athimue.data.network.dto.artist.toArtist
import com.athimue.data.network.dto.searchAlbum.SearchAlbumDto
import com.athimue.data.network.dto.searchAlbum.toAlbum
import com.athimue.data.network.dto.searchArtist.SearchArtistDto
import com.athimue.data.network.dto.searchArtist.toArtist
import com.athimue.data.network.dto.searchTrack.SearchTrackDto
import com.athimue.data.network.dto.searchTrack.toTrack
import com.athimue.data.network.dto.track.toTrack
import com.athimue.domain.model.Album
import com.athimue.domain.model.Artist
import com.athimue.domain.model.Track
import com.athimue.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val deezerApi: DeezerApi
) : SearchRepository {

    override suspend fun getTrack(id: Long): Flow<Result<Track>> {
        return flow {
            runCatching {
                val response = deezerApi.getTrack(id)
                response.takeIf { it.isSuccessful }?.body()?.let {
                    emit(Result.success(it.toTrack()))
                } ?: emit(Result.failure(Throwable("No data")))
            }.getOrElse {
                emit(Result.failure(Throwable(it.toString())))
            }
        }
    }

    override suspend fun getTracks(query: String): Flow<Result<List<Track>>> {
        return flow {
            runCatching {
                val response = deezerApi.getSearchedTracks(query)
                response.takeIf { it.isSuccessful }?.body()?.let {
                    emit(Result.success(it.data.map(SearchTrackDto::toTrack)))
                } ?: emit(Result.failure(Throwable("No data")))
            }.getOrElse {
                emit(Result.failure(Throwable(it.toString())))
            }
        }
    }

    override suspend fun getAlbum(id: Long): Flow<Result<Album>> {
        return flow {
            runCatching {
                val response = deezerApi.getAlbum(id)
                response.takeIf { it.isSuccessful }?.body()?.let {
                    emit(Result.success(it.toAlbum()))
                } ?: emit(Result.failure(Throwable("No data")))
            }.getOrElse {
                emit(Result.failure(Throwable(it.toString())))
            }
        }
    }

    override suspend fun getAlbums(query: String): Flow<Result<List<Album>>> {
        return flow {
            runCatching {
                val response = deezerApi.getSearchedAlbums(query)
                response.takeIf { it.isSuccessful }?.body()?.let {
                    emit(Result.success(it.data.map(SearchAlbumDto::toAlbum)))
                } ?: emit(Result.failure(Throwable("No data")))
            }.getOrElse {
                emit(Result.failure(Throwable(it.toString())))
            }
        }
    }

    override suspend fun getArtist(id: Long): Flow<Result<Artist>> {
        return flow {
            runCatching {
                val response = deezerApi.getArtist(id)
                response.takeIf { it.isSuccessful }?.body()?.let {
                    emit(Result.success(it.toArtist()))
                } ?: emit(Result.failure(Throwable("No data")))
            }.getOrElse {
                emit(Result.failure(Throwable(it.toString())))
            }
        }
    }

    override suspend fun getArtists(query: String): Flow<Result<List<Artist>>> {
        return flow {
            runCatching {
                val response = deezerApi.getSearchedArtists(query)
                response.takeIf { it.isSuccessful }?.body()?.let {
                    emit(Result.success(it.data.map(SearchArtistDto::toArtist)))
                } ?: emit(Result.failure(Throwable("No data")))
            }.getOrElse {
                emit(Result.failure(Throwable(it.toString())))
            }
        }
    }

    override suspend fun getPlaylists(query: String): Flow<Result<List<Track>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPodcasts(query: String): Flow<Result<List<Track>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRadios(query: String): Flow<Result<List<Track>>> {
        TODO("Not yet implemented")
    }


}