package com.athimue.data.network.dto.album

import com.google.gson.annotations.SerializedName

data class GenreListAlbumDto(
    @SerializedName("data") val data: List<GenreAlbumDto>,
)