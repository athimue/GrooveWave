package com.athimue.data.network.dto.chartTrack

import com.google.gson.annotations.SerializedName

data class ChartTrackListDto(
    @SerializedName("data") val data: List<ChartTrackDto>,
    @SerializedName("total") val total: Int,
)