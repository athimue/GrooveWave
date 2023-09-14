package com.athimue.data.database

import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.athimue.data.database.converter.TrackListConverter
import com.athimue.data.database.dao.AlbumDao
import com.athimue.data.database.dao.ArtistDao
import com.athimue.data.database.dao.PlaylistDao
import com.athimue.data.database.dao.TrackDao
import com.athimue.data.database.entity.AlbumEntity
import com.athimue.data.database.entity.ArtistEntity
import com.athimue.data.database.entity.PlaylistEntity
import com.athimue.data.database.entity.TrackEntity

/**
 * Class that defines the database room.
 */
@androidx.room.Database(
    entities = [AlbumEntity::class, ArtistEntity::class, TrackEntity::class, PlaylistEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TrackListConverter::class)
abstract class Database : RoomDatabase() {

    abstract fun albumDao(): AlbumDao

    abstract fun artistDao(): ArtistDao

    abstract fun trackDao(): TrackDao

    abstract fun playlistDao(): PlaylistDao

    companion object {
        const val DATABASE_NAME: String = "database.db"
    }
}
