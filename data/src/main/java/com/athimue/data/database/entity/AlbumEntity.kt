package com.athimue.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.athimue.domain.model.Album

@Entity(tableName = "album")
data class AlbumEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val cover: String,
)

fun AlbumEntity.toAlbum() = Album(
    id = id,
    name = name,
    cover = cover
)