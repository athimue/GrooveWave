package com.athimue.app.composable.artist

import com.athimue.domain.model.Artist

data class ArtistUiModel(
    val name: String,
    val link: String,
    val cover: String,
    val nbAlbum: Int,
    val nbFan: Int
)

fun Artist.toArtistUiModel() = ArtistUiModel(
    name = name,
    link = link!!,
    cover = cover!!,
    nbAlbum = nbAlbum!!,
    nbFan = nbFan!!
)
