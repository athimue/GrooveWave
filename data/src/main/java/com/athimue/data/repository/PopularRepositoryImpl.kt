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
import com.athimue.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * [PopularRepository] implementation.
 */
class PopularRepositoryImpl @Inject constructor(
    private val deezerApi: DeezerApi
) : PopularRepository {

    override suspend fun getPopularTracks(): Flow<Resource<List<Track>>> {
        return flow {
            runCatching {
                val response = deezerApi.getChartTracks()
                response.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let {
                        emit(Resource.Success(it.data.map(TrackDto::toTrack)))
                    } ?: emit(Resource.Error("No data"))
            }.getOrElse {
                emit(Resource.Error(it.toString()))
            }
        }
    }

    override suspend fun getPopularArtists(): Flow<Resource<List<Artist>>> {
        return flow {
            runCatching {
                val response = deezerApi.getChartArtists()
                response.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let {
                        emit(Resource.Success(it.data.map(ArtistDto::toArtist)))
                    } ?: emit(Resource.Error("No data"))
            }.getOrElse {
                emit(Resource.Error(it.toString()))
            }
        }
    }

    override suspend fun getPopularAlbums(): Flow<Resource<List<Album>>> {
        return flow {
            runCatching {
                val response = deezerApi.getChartAlbums()
                response.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let {
                        emit(Resource.Success(it.data.map(AlbumDto::toAlbum)))
                    } ?: emit(Resource.Error("No data"))
            }.getOrElse {
                emit(Resource.Error(it.toString()))
            }
        }
    }

}
