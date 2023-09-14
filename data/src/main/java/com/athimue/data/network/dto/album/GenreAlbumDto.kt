package com.athimue.data.network.dto.album

import com.google.gson.annotations.SerializedName

data class GenreAlbumDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("picture") val picture: String,
    @SerializedName("type") val type: String
)