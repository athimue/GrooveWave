package com.athimue.data.network.dto.chartAlbum

import com.google.gson.annotations.SerializedName

data class ChartAlbumListDto(
    @SerializedName("data") var data: List<ChartAlbumDto>,
    @SerializedName("total") var total: Int,
    @SerializedName("next") val next: String?
)