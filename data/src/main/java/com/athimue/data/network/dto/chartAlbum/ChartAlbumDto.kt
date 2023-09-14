package com.athimue.data.network.dto.chartAlbum

import com.athimue.data.network.dto.chartArtist.ChartArtistDto
import com.athimue.domain.model.Album
import com.google.gson.annotations.SerializedName

data class ChartAlbumDto(
    @SerializedName("id") var id: Long,
    @SerializedName("title") var title: String,
    @SerializedName("link") var link: String?,
    @SerializedName("cover") var cover: String,
    @SerializedName("cover_small") var coverSmall: String,
    @SerializedName("cover_medium") var coverMedium: String,
    @SerializedName("cover_big") var coverBig: String,
    @SerializedName("cover_xl") var coverXl: String,
    @SerializedName("md5_image") var md5Image: String,
    @SerializedName("record_type") var recordType: String?,
    @SerializedName("tracklist") var tracklist: String,
    @SerializedName("explicit_lyrics") var explicitLyrics: Boolean?,
    @SerializedName("position") var position: Int?,
    @SerializedName("artist") var artist: ChartArtistDto?,
    @SerializedName("type") var type: String,
)

fun ChartAlbumDto.toAlbum() = Album(
    id = id,
    name = title,
    cover = coverXl,
)