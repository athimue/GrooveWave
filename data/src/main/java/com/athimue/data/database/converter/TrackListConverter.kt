package com.athimue.data.database.converter

import androidx.room.TypeConverter
import com.athimue.data.database.entity.TrackEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TrackListConverter {
    @TypeConverter
    fun fromJson(json: String): List<TrackEntity> {
        val type = object : TypeToken<List<TrackEntity>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun toJson(list: List<TrackEntity>): String {
        return Gson().toJson(list)
    }
}
