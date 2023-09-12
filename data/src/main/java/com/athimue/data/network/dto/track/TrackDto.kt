package com.athimue.data.network.dto.track

import com.athimue.data.network.dto.artist.ArtistDto
import com.athimue.data.network.dto.artist.toArtist
import com.athimue.domain.model.Track
import com.google.gson.annotations.SerializedName

data class TrackDto(
    @SerializedName("id") val id: Long,
    @SerializedName("readable") val readable: Boolean?,
    @SerializedName("title") val title: String,
    @SerializedName("title_short") val title_short: String,
    @SerializedName("title_version") val title_version: String,
    @SerializedName("link") val link: String,
    @SerializedName("duration") val duration: Int,
    @SerializedName("rank") val rank: Int,
    @SerializedName("explicit_lyrics") val explicitLyrics: Boolean,
    @SerializedName("explicit_content_lyrics") val explicitContentLyrics: Int,
    @SerializedName("explicit_content_cover") val explicitContentCover: Int,
    @SerializedName("md5_image") val md5Image: String,
    @SerializedName("preview") val preview: String,
    @SerializedName("position") val position: Int,
    @SerializedName("artist") val artist: ArtistDto,
    @SerializedName("album") val album: AlbumTrackDto,
    @SerializedName("type") val type: String
)

fun TrackDto.toTrack() = Track(
    id = id,
    title = title,
    titleShort = title_short,
    link = link,
    duration = duration,
    rank = rank,
    explicitLyrics = explicitLyrics,
    preview = preview,
    position = position,
    cover = album.cover,
    artist = artist.toArtist(),
    album = album.toAlbum()
)