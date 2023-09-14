package com.athimue.domain.model

data class Artist(
    val id: Long,
    val name: String,
    val link: String,
    val cover: String,
    val nbAlbum: Int?,
    val nbFan: Int?
)
