package com.athimue.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.athimue.data.database.entity.TrackEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TrackDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract suspend fun insert(track: TrackEntity): Long

    @Query("DELETE FROM track WHERE id IS :trackId")
    abstract suspend fun delete(trackId: Int): Int

    @Query("SELECT * FROM track ORDER BY id DESC")
    abstract fun getFavorites(): Flow<List<TrackEntity>>
}