package com.athimue.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.athimue.domain.model.Track

@Entity(tableName = "track")
data class TrackEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val cover: String,
    val link: String,
    val artist: ArtistEntity,
    val album: AlbumEntity
)

fun TrackEntity.toTrack() = Track(
    id = id,
    title = name,
    cover = cover,
    link = link,
    artist = artist.toArtist(),
    album = album.toAlbum()
)