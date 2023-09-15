package com.athimue.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.athimue.data.database.entity.AlbumEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class AlbumDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract suspend fun insert(albumEntity: AlbumEntity): Long

    @Query("DELETE FROM album WHERE id IS :albumId")
    abstract suspend fun delete(albumId: Long): Int

    @Query("SELECT * FROM album ORDER BY id DESC")
    abstract fun getFavorites(): Flow<List<AlbumEntity>>
}