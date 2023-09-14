package com.athimue.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.athimue.data.database.entity.ArtistEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ArtistDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract suspend fun insert(artistEntity: ArtistEntity): Long

    @Query("DELETE FROM artist WHERE id IS :artistId")
    abstract suspend fun delete(artistId: Int): Int

    @Query("SELECT * FROM artist")
    abstract fun getFavorites(): Flow<List<ArtistEntity>>
}