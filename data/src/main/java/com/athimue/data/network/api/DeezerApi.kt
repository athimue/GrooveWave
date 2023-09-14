package com.athimue.data.network.api

import com.athimue.data.network.dto.album.AlbumDto
import com.athimue.data.network.dto.artist.ArtistDto
import com.athimue.data.network.dto.chartAlbum.ChartAlbumListDto
import com.athimue.data.network.dto.chartArtist.ChartArtistListDto
import com.athimue.data.network.dto.chartTrack.ChartTrackListDto
import com.athimue.data.network.dto.searchAlbum.SearchAlbumListDto
import com.athimue.data.network.dto.searchArtist.SearchArtistListDto
import com.athimue.data.network.dto.searchTrack.SearchTrackListDto
import com.athimue.data.network.dto.track.TrackDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DeezerApi {

    @GET("chart/0/tracks")
    suspend fun getChartTracks(): Response<ChartTrackListDto>

    @GET("chart/0/albums")
    suspend fun getChartAlbums(): Response<ChartAlbumListDto>

    @GET("chart/0/artists")
    suspend fun getChartArtists(): Response<ChartArtistListDto>

    @GET("track/{trackId}")
    suspend fun getTrack(@Path("trackId") trackId: Long): Response<TrackDto>

    @GET("artist/{artistId}")
    suspend fun getArtist(@Path("artistId") artistId: Long): Response<ArtistDto>

    @GET("album/{albumId}")
    suspend fun getAlbum(@Path("albumId") albumId: Long): Response<AlbumDto>

    @GET("search/track")
    suspend fun getSearchedTracks(@Query("q") query: String): Response<SearchTrackListDto>

    @GET("search/album")
    suspend fun getSearchedAlbums(@Query("q") query: String): Response<SearchAlbumListDto>

    @GET("search/artist")
    suspend fun getSearchedArtists(@Query("q") query: String): Response<SearchArtistListDto>

    @GET("search/playlist")
    suspend fun getSearchedPlaylists(@Query("q") query: String): Response<ChartTrackListDto>

    @GET("search/radio")
    suspend fun getSearchedRadios(@Query("q") query: String): Response<ChartTrackListDto>

    @GET("search/podcast")
    suspend fun getSearchedPodcasts(@Query("q") query: String): Response<ChartTrackListDto>

    companion object {
        const val DEEZER_BASE_URL = "https://api.deezer.com"
    }
}