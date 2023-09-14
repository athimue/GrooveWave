package com.athimue.data.network.dto.searchArtist

import com.google.gson.annotations.SerializedName

data class SearchArtistListDto(
    @SerializedName("data") val data: List<SearchArtistDto>,
    @SerializedName("total") val total: Int,
    @SerializedName("next") val next: String,
)