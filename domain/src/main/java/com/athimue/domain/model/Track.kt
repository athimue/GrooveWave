package com.athimue.domain.model

data class Track(
    val id: Long,
    val title: String,
    val link: String,
    val duration: Int,
    val cover: String,
    val artist: Artist,
    val album: Album
)