package com.athimue.domain.model

data class SearchResult(
    val id: String,
    val readable: Boolean,
    val title: String,
    val titleShort: String,
    val titleVersion: String,
    val link: String,
    val duration: String,
    val rank: String,
    val explicitLyrics: Boolean,
    val explicitContentLyrics: Long,
    val explicitContentCover: Long,
    val preview: String,
    val md5Image: String,
    val artist: Artist,
    val album: Album,
    val type: String,
)
