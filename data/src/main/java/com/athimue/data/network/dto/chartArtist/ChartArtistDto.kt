package com.athimue.data.network.dto.chartArtist

import com.athimue.domain.model.Artist
import com.google.gson.annotations.SerializedName

data class ChartArtistDto(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("link") val link: String,
    @SerializedName("picture") val picture: String,
    @SerializedName("picture_small") val pictureSmall: String,
    @SerializedName("picture_medium") val pictureMedium: String,
    @SerializedName("picture_big") val pictureBig: String,
    @SerializedName("picture_xl") val pictureXl: String,
    @SerializedName("radio") val radio: Boolean,
    @SerializedName("tracklist") val tracklist: String,
    @SerializedName("type") val type: String
)

fun ChartArtistDto.toArtist() = Artist(
    id = id,
    name = name,
    link = link,
    cover = pictureBig,
    nbAlbum = null
)