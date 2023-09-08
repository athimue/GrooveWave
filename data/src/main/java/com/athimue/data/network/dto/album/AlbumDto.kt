package com.athimue.data.network.dto.album

import com.athimue.data.network.dto.artist.ArtistDto
import com.athimue.domain.model.Album
import com.google.gson.annotations.SerializedName

data class AlbumDto(
    @SerializedName("id") var id: Long,
    @SerializedName("title") var title: String,
    @SerializedName("link") var link: String?,
    @SerializedName("cover") var cover: String,
    @SerializedName("cover_small") var cover_small: String,
    @SerializedName("cover_medium") var cover_medium: String,
    @SerializedName("cover_big") var cover_big: String,
    @SerializedName("cover_xl") var cover_xl: String,
    @SerializedName("md5_image") var md5_image: String,
    @SerializedName("record_type") var record_type: String?,
    @SerializedName("tracklist") var tracklist: String,
    @SerializedName("explicit_lyrics") var explicit_lyrics: Boolean?,
    @SerializedName("position") var position: Int?,
    @SerializedName("artist") var artist: ArtistDto?,
    @SerializedName("type") var type: String,
)

fun AlbumDto.toAlbum() = Album(
    id = id,
    name = title,
    cover = cover
)