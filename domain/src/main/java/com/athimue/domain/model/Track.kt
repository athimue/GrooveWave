package com.athimue.domain.model

data class Track(
    val id: Long,
    val title: String,
    val titleShort: String,
    val link: String,
    val duration: Int,
    val rank: Int,
    val explicitLyrics: Boolean,
    val preview: String,
    val position: Int,
    val cover: String,
    val artist: Artist,
    val album: Album
)