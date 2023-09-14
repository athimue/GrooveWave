package com.athimue.data.network.dto.track

import com.athimue.data.network.dto.chartArtist.ChartArtistDto
import com.athimue.data.network.dto.chartArtist.toArtist
import com.athimue.data.network.dto.chartTrack.ChartAlbumTrackDto
import com.athimue.data.network.dto.chartTrack.toAlbum
import com.athimue.domain.model.Track
import com.google.gson.annotations.SerializedName

data class TrackDto(
    @SerializedName("id") val id: String,
    @SerializedName("readable") val readable: Boolean,
    @SerializedName("title") val title: String,
    @SerializedName("title_short") val title_short: String,
    @SerializedName("title_version") val title_version: String,
    @SerializedName("isrc") val isrc: String,
    @SerializedName("link") val link: String,
    @SerializedName("share") val share: String,
    @SerializedName("duration") val duration: String,
    @SerializedName("track_position") val trackPosition: Int,
    @SerializedName("disk_number") val diskNumber: Int,
    @SerializedName("rank") val rank: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("explicit_lyrics") val explicitLyrics: Boolean,
    @SerializedName("explicit_content_lyrics") val explicitContentLyrics: Int,
    @SerializedName("explicit_content_cover") val explicitContentCover: Int,
    @SerializedName("preview") val preview: String,
    @SerializedName("bpm") val bpm: Float,
    @SerializedName("gain") val gain: Float,
    @SerializedName("available_countries") val availableCountries: List<String>,
    @SerializedName("contributors") val contributors: List<Contributor>,
    @SerializedName("md5_image") val md5Image: String,
    @SerializedName("artist") val artist: ChartArtistDto,
    @SerializedName("album") val album: ChartAlbumTrackDto,
    @SerializedName("type") val type: String
)

fun TrackDto.toTrack() = Track(
    id = id.toLong(),
    title = title,
    titleShort = title_short,
    link = link,
    duration = duration.toInt(),
    rank = rank.toInt(),
    explicitLyrics = explicitLyrics,
    preview = preview,
    position = trackPosition,
    cover = album.coverXl,
    artist = artist.toArtist(),
    album = album.toAlbum()
)