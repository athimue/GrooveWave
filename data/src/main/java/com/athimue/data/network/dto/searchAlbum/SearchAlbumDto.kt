package com.athimue.data.network.dto.searchAlbum

import com.athimue.data.network.dto.album.*
import com.athimue.data.network.dto.chartArtist.ChartArtistDto
import com.athimue.domain.model.Album
import com.google.gson.annotations.SerializedName

data class SearchAlbumDto(
    @SerializedName("id") var id: Long,
    @SerializedName("title") var title: String,
    @SerializedName("link") var link: String,
    @SerializedName("cover") var cover: String,
    @SerializedName("cover_small") var coverSmall: String,
    @SerializedName("cover_medium") var coverMedium: String,
    @SerializedName("cover_big") var coverBig: String,
    @SerializedName("cover_xl") var coverXl: String,
    @SerializedName("md5_image") var md5Image: String,
    @SerializedName("genre_id") var genreId: Int,
    @SerializedName("nb_tracks") var nbTracks: Int,
    @SerializedName("record_type") var recordType: String,
    @SerializedName("tracklist") var tracklist: String,
    @SerializedName("explicit_lyrics") var explicitLyrics: Boolean,
    @SerializedName("artist") var artist: ChartArtistDto,
    @SerializedName("type") var type: String,
)

fun SearchAlbumDto.toAlbum() = Album(
    id = id,
    name = title,
    cover = coverXl,
    genreId = genreId,
    nbTracks = nbTracks
)