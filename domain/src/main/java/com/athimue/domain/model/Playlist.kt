package com.athimue.domain.model

data class Playlist(
    val id: Int,
    val name: String,
    val tracks: List<Long>
)
