package com.athimue.data.network.dto.artist

import com.athimue.domain.model.Artist
import com.google.gson.annotations.SerializedName

data class ArtistDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("link") val link: String,
    @SerializedName("share") val share: String,
    @SerializedName("picture") val picture: String,
    @SerializedName("picture_small") val pictureSmall: String,
    @SerializedName("picture_medium") val pictureMedium: String,
    @SerializedName("picture_big") val pictureBig: String,
    @SerializedName("picture_xl") val pictureXl: String,
    @SerializedName("nb_album") val nbAlbum: Int,
    @SerializedName("nb_fan") val nbFan: Int,
    @SerializedName("radio") val radio: Boolean,
    @SerializedName("tracklist") val tracklist: String,
    @SerializedName("type") val type: String
)

fun ArtistDto.toArtist() = Artist(
    id = id.toLong(),
    name = name,
    link = link,
    cover = pictureXl,
    nbAlbum = nbAlbum,
    nbFan = nbFan,
)