package com.athimue.data.network.api

import com.athimue.data.network.dto.album.AlbumListDto
import com.athimue.data.network.dto.artist.ArtistListDto
import com.athimue.data.network.dto.track.TrackDetailDto
import com.athimue.data.network.dto.track.TrackListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DeezerApi {

    @GET("chart/0/tracks")
    suspend fun getChartTracks(): Response<TrackListDto>

    @GET("chart/0/albums")
    suspend fun getChartAlbums(): Response<AlbumListDto>

    @GET("chart/0/artists")
    suspend fun getChartArtists(): Response<ArtistListDto>

    @GET("track/{trackID}")
    suspend fun getTrack(@Path("trackID") trackId: Long): Response<TrackDetailDto>

    @GET("search/track")
    suspend fun getSearchedTracks(@Query("q") query: String): Response<TrackListDto>

    @GET("search/album")
    suspend fun getSearchedAlbums(@Query("q") query: String): Response<AlbumListDto>

    @GET("search/artist")
    suspend fun getSearchedArtists(@Query("q") query: String): Response<ArtistListDto>

    @GET("search/playlist")
    suspend fun getSearchedPlaylists(@Query("q") query: String): Response<TrackListDto>

    @GET("search/radio")
    suspend fun getSearchedRadios(@Query("q") query: String): Response<TrackListDto>

    @GET("search/podcast")
    suspend fun getSearchedPodcasts(@Query("q") query: String): Response<TrackListDto>

    companion object {
        const val DEEZER_BASE_URL = "https://api.deezer.com"
    }
}