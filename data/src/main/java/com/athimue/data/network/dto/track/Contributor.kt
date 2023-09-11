package com.athimue.data.network.dto.track

import com.google.gson.annotations.SerializedName

data class Contributor(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val readable: Boolean,
    @SerializedName("link") val title: String,
    @SerializedName("share") val title_short: String,
    @SerializedName("picture") val title_version: String,
    @SerializedName("picture_small") val isrc: String,
    @SerializedName("picture_medium") val link: String,
    @SerializedName("picture_big") val share: String,
    @SerializedName("picture_xl") val duration: String,
    @SerializedName("radio") val radio: Boolean,
    @SerializedName("tracklist") val tracklist: String,
    @SerializedName("type") val type: String,
    @SerializedName("role") val role: String,
)
