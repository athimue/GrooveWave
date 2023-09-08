package com.athimue.data.network.dto.artist

import com.google.gson.annotations.SerializedName

data class ArtistListDto(
    @SerializedName("data") var data: List<ArtistDto>,
    @SerializedName("total") var total: Int,
    @SerializedName("next") val next: String?
)