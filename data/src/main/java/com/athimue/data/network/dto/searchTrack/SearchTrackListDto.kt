package com.athimue.data.network.dto.searchTrack

import com.google.gson.annotations.SerializedName

data class SearchTrackListDto(
    @SerializedName("data") val data: List<SearchTrackDto>,
    @SerializedName("total") val total: Int,
    @SerializedName("next") val next: String,
)