package com.athimue.data.network.dto.track

import com.google.gson.annotations.SerializedName

data class TrackListDto(
    @SerializedName("data") val data: List<TrackDto>,
    @SerializedName("total") val total: Int,
    @SerializedName("next") val next: String?
)