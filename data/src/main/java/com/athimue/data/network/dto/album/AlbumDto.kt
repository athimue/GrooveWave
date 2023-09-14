package com.athimue.data.network.dto.album

import com.athimue.data.network.dto.track.Contributor
import com.athimue.domain.model.Album
import com.google.gson.annotations.SerializedName

data class AlbumDto(
    @SerializedName("id") var id: String,
    @SerializedName("title") var title: String,
    @SerializedName("upc") var upc: String,
    @SerializedName("link") var link: String,
    @SerializedName("share") var share: String,
    @SerializedName("cover") var cover: String,
    @SerializedName("cover_small") var coverSmall: String,
    @SerializedName("cover_medium") var coverMedium: String,
    @SerializedName("cover_big") var coverBig: String,
    @SerializedName("cover_xl") var coverXl: String,
    @SerializedName("md5_image") var md5Image: String,
    @SerializedName("genre_id") var genreId: Int,
    @SerializedName("genres") var genres: GenreListAlbumDto,
    @SerializedName("label") var label: String,
    @SerializedName("nb_tracks") var nbTracks: Int,
    @SerializedName("duration") var duration: Int,
    @SerializedName("fans") var fans: Int,
    @SerializedName("release_date") var releaseDate: String,
    @SerializedName("record_type") var recordType: String,
    @SerializedName("available") var available: Boolean,
    @SerializedName("tracklist") var tracklist: String,
    @SerializedName("explicit_lyrics") var explicitLyrics: Boolean,
    @SerializedName("explicit_content_lyrics") var explicitContentLyrics: Int,
    @SerializedName("explicit_content_cover") var explicitContentCover: Int,
    @SerializedName("contributors") val contributors: List<Contributor>,
    @SerializedName("artist") var artist: ArtistAlbumDto,
    @SerializedName("type") var type: String,
    @SerializedName("tracks") var tracks: TrackListAlbumDto,
)

fun AlbumDto.toAlbum() = Album(
    id = id.toLong(),
    name = title,
    cover = coverXl,
    genreId = genreId,
    nbTracks = nbTracks
)