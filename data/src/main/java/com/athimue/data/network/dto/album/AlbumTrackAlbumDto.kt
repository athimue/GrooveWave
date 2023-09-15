package com.athimue.data.network.dto.album

import com.athimue.domain.model.Album
import com.google.gson.annotations.SerializedName

data class AlbumTrackAlbumDto(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("cover") val cover: String,
    @SerializedName("cover_small") val coverSmall: String,
    @SerializedName("cover_medium") val coverMedium: String,
    @SerializedName("cover_big") val coverBig: String,
    @SerializedName("cover_xl") val coverXl: String,
    @SerializedName("md5_image") val md5Image: String,
    @SerializedName("tracklist") val tracklist: String,
    @SerializedName("type") val type: String
)

fun AlbumTrackAlbumDto.toAlbum() = Album(
    id = id.toLong(),
    name = title,
    link = null,
    cover = coverXl,
    genres = null,
    label = null,
    duration = null,
    nbTracks = null,
    nbFans = null,
    releaseDate = null,
    available = null,
    explicitLyrics = null,
    artist = null,
    tracks = null
)