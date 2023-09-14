package com.athimue.data.network.dto.chartTrack

import com.athimue.domain.model.Album
import com.google.gson.annotations.SerializedName

data class ChartAlbumTrackDto(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("link") val link: String,
    @SerializedName("cover") val cover: String,
    @SerializedName("cover_small") val coverSmall: String,
    @SerializedName("cover_medium") val coverMedium: String,
    @SerializedName("cover_big") val coverBig: String,
    @SerializedName("cover_xl") val coverXl: String,
    @SerializedName("md5_image") val md5Image: String,
    @SerializedName("tracklist") val tracklist: String,
    @SerializedName("type") val type: String
)

fun ChartAlbumTrackDto.toAlbum() = Album(
    id = id,
    name = title,
    cover = coverXl,
)