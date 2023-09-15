package com.athimue.domain.model

data class Album(
    val id: Long,
    val name: String,
    val link: String?,
    val cover: String,
    val genres: List<Genre>?,
    val label: String?,
    val duration: Int?,
    val nbTracks: Int?,
    val nbFans: Int?,
    val releaseDate: String?,
    val available: Boolean?,
    val explicitLyrics: Boolean?,
    val artist: Artist?,
    val tracks: List<Track>?
)
