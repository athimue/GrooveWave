package com.athimue.data.repository

import com.athimue.data.network.api.DeezerApi
import com.athimue.data.network.dto.chartAlbum.ChartAlbumDto
import com.athimue.data.network.dto.chartAlbum.toAlbum
import com.athimue.data.network.dto.chartArtist.ChartArtistDto
import com.athimue.data.network.dto.chartArtist.toArtist
import com.athimue.data.network.dto.chartTrack.ChartTrackDto
import com.athimue.data.network.dto.chartTrack.toTrack
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
                        emit(Result.success(it.data.map(ChartTrackDto::toTrack)))
                    } ?: run {
                    emit(Result.failure(Throwable(response.message())))
                }
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
                        emit(Result.success(it.data.map(ChartArtistDto::toArtist)))
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
                        emit(Result.success(it.data.map(ChartAlbumDto::toAlbum)))
                    } ?: emit(Result.failure(Throwable("No data")))
            }.getOrElse {
                emit(Result.failure(Throwable(it.toString())))
            }
        }
    }

}
