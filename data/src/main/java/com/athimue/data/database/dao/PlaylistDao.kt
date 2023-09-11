package com.athimue.data.database.dao

import androidx.room.*
import com.athimue.data.database.entity.PlaylistEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class PlaylistDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract suspend fun insert(playlistEntity: PlaylistEntity): Long

    @Update(onConflict = OnConflictStrategy.ABORT)
    abstract suspend fun update(playlistEntity: PlaylistEntity): Int

    @Query("DELETE FROM playlist WHERE id IS :playlistId")
    abstract suspend fun delete(playlistId: Int): Int

    @Query("SELECT * FROM playlist ORDER BY id DESC")
    abstract fun getPlaylists(): Flow<List<PlaylistEntity>>

    @Query("SELECT * FROM playlist WHERE id IS :playlistId")
    abstract fun getPlaylist(playlistId: Int): Flow<PlaylistEntity>
}