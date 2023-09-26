package com.athimue.app.composable.album

import com.athimue.domain.model.Album
import com.athimue.domain.model.Artist
import com.athimue.domain.model.Genre
import com.athimue.domain.model.Track

data class AlbumUiModel(
    val id: Long,
    val name: String,
    val cover: String,
    val genres: List<Genre>,
    val label: String,
    val duration: Int,
    val nbTracks: Int,
    val nbFans: Int,
    val releaseDate: String,
    val available: Boolean,
    val explicitLyrics: Boolean,
    val artist: Artist,
    val tracks: List<Track>
)

fun Album.toAlbumUiModel() = AlbumUiModel(
    id = id,
    name = name,
    cover = cover,
    genres = genres!!,
    label = label!!,
    duration = duration!!,
    nbTracks = nbTracks!!,
    nbFans = nbFans!!,
    releaseDate = releaseDate!!,
    available = available!!,
    explicitLyrics = explicitLyrics!!,
    artist = artist!!,
    tracks = tracks!!,
)