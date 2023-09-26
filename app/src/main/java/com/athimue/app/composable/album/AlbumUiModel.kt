package com.athimue.app.composable.album

import com.athimue.domain.model.Album

data class AlbumUiModel(
    val id: Long,
    val name: String,
    val cover: String,
    val genreNames: List<String>,
    val label: String,
    val duration: Int,
    val nbTracks: Int,
    val nbFans: Int,
    val releaseDate: String,
    val available: Boolean,
    val explicitLyrics: Boolean,
    val artistName: String,
    val trackNames: List<String>
)

fun Album.toAlbumUiModel() = AlbumUiModel(
    id = id,
    name = name,
    cover = cover,
    genreNames = genres!!.map { it.name },
    label = label!!,
    duration = duration!!,
    nbTracks = nbTracks!!,
    nbFans = nbFans!!,
    releaseDate = releaseDate!!,
    available = available!!,
    explicitLyrics = explicitLyrics!!,
    artistName = artist!!.name,
    trackNames = tracks!!.map { it.title },
)