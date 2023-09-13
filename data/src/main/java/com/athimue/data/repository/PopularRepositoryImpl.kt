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
import com.athimue.domain.repository.PopularRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * [PopularRepository] implementation.
 */
class PopularRepositoryImpl @Inject constructor(
    private val deezerApi: DeezerApi
) : PopularRepository {

    override suspend fun getPopularTracks(): Flow<Result<List<Track>>> {
        return flow {
            runCatching {
                val response = deezerApi.getChartTracks()
                response.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let {
                        emit(Result.success(it.data.map(TrackDto::toTrack)))
                    } ?: emit(Result.failure(Throwable("No data")))
            }.getOrElse {
                emit(Result.failure(Throwable(it.toString())))
            }
        }
    }

    override suspend fun getPopularArtists(): Flow<Result<List<Artist>>> {
        return flow {
            runCatching {
                val response = deezerApi.getChartArtists()
                response.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let {
                        emit(Result.success(it.data.map(ArtistDto::toArtist)))
                    } ?: emit(Result.failure(Throwable("No data")))
            }.getOrElse {
                emit(Result.failure(Throwable(it.toString())))
            }
        }
    }

    override suspend fun getPopularAlbums(): Flow<Result<List<Album>>> {
        return flow {
            runCatching {
                val response = deezerApi.getChartAlbums()
                response.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let {
                        emit(Result.success(it.data.map(AlbumDto::toAlbum)))
                    } ?: emit(Result.failure(Throwable("No data")))
            }.getOrElse {
                emit(Result.failure(Throwable(it.toString())))
            }
        }
    }

}
