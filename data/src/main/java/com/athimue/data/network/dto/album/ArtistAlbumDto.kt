package com.athimue.data.network.dto.album

import com.athimue.domain.model.Artist
import com.google.gson.annotations.SerializedName

data class ArtistAlbumDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("picture") val picture: String,
    @SerializedName("picture_small") val pictureSmall: String,
    @SerializedName("picture_medium") val pictureMedium: String,
    @SerializedName("picture_big") val pictureBig: String,
    @SerializedName("picture_xl") val pictureXl: String,
    @SerializedName("nb_album") val nbAlbum: Int,
    @SerializedName("tracklist") val tracklist: String,
    @SerializedName("type") val type: String
)

fun ArtistAlbumDto.toArtist() = Artist(
    id = id.toLong(),
    name = name,
    link = null,
    cover = pictureXl,
    nbAlbum = nbAlbum,
    nbFan = null
)