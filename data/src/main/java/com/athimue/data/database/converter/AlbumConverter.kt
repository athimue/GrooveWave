package com.athimue.data.database.converter

import androidx.room.TypeConverter
import com.athimue.data.database.entity.AlbumEntity
import com.google.gson.Gson
import org.json.JSONObject

class AlbumConverter {
    @TypeConverter
    fun fromAlbum(album: AlbumEntity): String {
        return JSONObject().apply {
            put("id", album.id)
            put("name", album.name)
            put("cover", album.cover)
        }.toString()
    }

    @TypeConverter
    fun toAlbum(album: String): AlbumEntity {
        return Gson().fromJson(album, AlbumEntity::class.java)
    }
}
