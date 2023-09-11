package com.athimue.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.athimue.domain.model.Playlist

@Entity(tableName = "playlist")
data class PlaylistEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val tracks: List<TrackEntity>,
)

fun PlaylistEntity.toPlaylist() = Playlist(
    id = id,
    name = name,
    tracks = tracks.map { it.id }
)

fun Playlist.toPlaylistEntity() = PlaylistEntity(
    id = id,
    name = name,
    tracks = tracks.map { TrackEntity(it) }
)