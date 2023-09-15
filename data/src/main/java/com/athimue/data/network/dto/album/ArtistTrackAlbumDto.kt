package com.athimue.data.network.dto.album

import com.athimue.domain.model.Artist
import com.google.gson.annotations.SerializedName

data class ArtistTrackAlbumDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("tracklist") val album: String,
    @SerializedName("type") val type: String
)

fun ArtistTrackAlbumDto.toArtist() = Artist(
    id = id.toLong(),
    name = name,
    link = null,
    cover = null,
    nbAlbum = null,
    nbFan = null
)