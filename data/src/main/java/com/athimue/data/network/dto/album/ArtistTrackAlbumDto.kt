package com.athimue.data.network.dto.album

import com.google.gson.annotations.SerializedName

data class ArtistTrackAlbumDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("tracklist") val album: String,
    @SerializedName("type") val type: String
)