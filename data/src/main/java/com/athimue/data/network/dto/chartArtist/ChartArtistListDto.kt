package com.athimue.data.network.dto.chartArtist

import com.google.gson.annotations.SerializedName

data class ChartArtistListDto(
    @SerializedName("data") var data: List<ChartArtistDto>,
    @SerializedName("total") var total: Int,
    @SerializedName("next") val next: String?
)