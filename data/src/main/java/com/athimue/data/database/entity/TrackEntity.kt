package com.athimue.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "track")
data class TrackEntity(
    @PrimaryKey val id: Long,
)
