package com.athimue.data.network.dto.album

import com.google.gson.annotations.SerializedName

data class TrackAlbumDto(
    @SerializedName("id") val id: String,
    @SerializedName("readable") val readable: Boolean,
    @SerializedName("title") val title: String,
    @SerializedName("title_short") val title_short: String,
    @SerializedName("title_version") val title_version: String,
    @SerializedName("link") val link: String,
    @SerializedName("duration") val duration: String,
    @SerializedName("rank") val rank: String,
    @SerializedName("explicit_lyrics") val explicitLyrics: Boolean,
    @SerializedName("explicit_content_lyrics") val explicitContentLyrics: Int,
    @SerializedName("explicit_content_cover") val explicitContentCover: Int,
    @SerializedName("preview") val preview: String,
    @SerializedName("md5_image") val md5Image: String,
    @SerializedName("artist") val artist: ArtistTrackAlbumDto,
    @SerializedName("album") val album: AlbumTrackAlbumDto,
    @SerializedName("type") val type: String
)