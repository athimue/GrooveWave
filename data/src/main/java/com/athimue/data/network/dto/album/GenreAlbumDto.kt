package com.athimue.data.network.dto.album

import com.athimue.domain.model.Genre
import com.google.gson.annotations.SerializedName

data class GenreAlbumDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("picture") val picture: String,
    @SerializedName("type") val type: String
)

fun GenreAlbumDto.toGenre() = Genre(
    id = id, name = name, picture = picture
)