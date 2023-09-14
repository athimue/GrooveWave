package com.athimue.data.network.dto.searchAlbum

import com.google.gson.annotations.SerializedName

data class SearchAlbumListDto(
    @SerializedName("data") val data: List<SearchAlbumDto>,
    @SerializedName("total") val total: Int,
    @SerializedName("next") val next: String,
)