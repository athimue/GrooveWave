package com.athimue.data.network.dto.searchTrack

import com.athimue.data.network.dto.album.AlbumTrackAlbumDto
import com.athimue.data.network.dto.album.toAlbum
import com.athimue.data.network.dto.chartArtist.ChartArtistDto
import com.athimue.data.network.dto.chartArtist.toArtist
import com.athimue.domain.model.Track
import com.google.gson.annotations.SerializedName

data class SearchTrackDto(
    @SerializedName("id") val id: Long,
    @SerializedName("readable") val readable: Boolean,
    @SerializedName("title") val title: String,
    @SerializedName("title_short") val title_short: String,
    @SerializedName("title_version") val title_version: String,
    @SerializedName("link") val link: String,
    @SerializedName("duration") val duration: Int,
    @SerializedName("rank") val rank: String,
    @SerializedName("explicit_lyrics") val explicitLyrics: Boolean,
    @SerializedName("explicit_content_lyrics") val explicitContentLyrics: Int,
    @SerializedName("explicit_content_cover") val explicitContentCover: Int,
    @SerializedName("preview") val preview: String,
    @SerializedName("md5_image") val md5Image: String,
    @SerializedName("artist") val artist: ChartArtistDto,
    @SerializedName("album") val album: AlbumTrackAlbumDto,
    @SerializedName("type") val type: String
)

fun SearchTrackDto.toTrack() = Track(
    id = id,
    title = title,
    titleShort = title_short,
    link = link,
    duration = duration,
    rank = rank.toInt(),
    explicitLyrics = explicitLyrics,
    preview = preview,
    position = null,
    cover = album.coverXl,
    artist = artist.toArtist(),
    album = album.toAlbum()
)