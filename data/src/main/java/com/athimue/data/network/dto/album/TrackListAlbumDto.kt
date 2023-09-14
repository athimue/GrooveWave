package com.athimue.data.network.dto.album

import com.google.gson.annotations.SerializedName

data class TrackListAlbumDto(
    @SerializedName("data") val data: List<TrackAlbumDto>,
)