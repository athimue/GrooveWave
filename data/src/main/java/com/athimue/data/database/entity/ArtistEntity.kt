package com.athimue.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.athimue.domain.model.Artist

@Entity(tableName = "artist")
data class ArtistEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val cover: String,
    val link: String
)

fun ArtistEntity.toArtist() = Artist(
    id = id,
    name = name,
    cover = cover,
    link = link
)

fun Artist.toArtistEntity() = ArtistEntity(
    id = id,
    name = name,
    cover = cover,
    link = link,
)