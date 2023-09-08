package com.athimue.data.network.dto.album

import com.google.gson.annotations.SerializedName

data class AlbumListDto(
    @SerializedName("data") var data: List<AlbumDto>,
    @SerializedName("total") var total: Int,
    @SerializedName("next") val next: String?
)