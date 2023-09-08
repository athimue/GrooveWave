package com.athimue.data.di

import android.content.Context
import androidx.room.Room
import com.athimue.data.database.Database
import com.athimue.data.database.dao.AlbumDao
import com.athimue.data.database.dao.ArtistDao
import com.athimue.data.database.dao.PlaylistDao
import com.athimue.data.database.dao.TrackDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import java.util.*
import javax.inject.Singleton

/**
 * Object that groups the singletons of database.
 */
@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    /**
     * Creation of the Database instance.
     */
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): Database = runBlocking {
        val builder = Room.databaseBuilder(
            context,
            Database::class.java,
            Database.DATABASE_NAME
        )
        builder.enableMultiInstanceInvalidation()
        builder.build()
    }

    /**
     * Creation of the FavoriteDao instance.
     *
     * @param database instance of a database
     * @return FavoriteDao Dao of the table 'favorite'
     */
    @Singleton
    @Provides
    fun provideFavoriteAlbumDao(database: Database): AlbumDao = database.albumDao()

    @Singleton
    @Provides
    fun provideFavoriteArtistDao(database: Database): ArtistDao = database.artistDao()

    @Singleton
    @Provides
    fun provideFavoriteTrackDao(database: Database): TrackDao = database.trackDao()

    @Singleton
    @Provides
    fun providePlaylistDao(database: Database): PlaylistDao = database.playlistDao()
}
