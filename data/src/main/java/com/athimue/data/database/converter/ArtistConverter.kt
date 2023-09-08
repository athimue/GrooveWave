package com.athimue.data.database.converter

import androidx.room.TypeConverter
import com.athimue.data.database.entity.ArtistEntity
import org.json.JSONObject

class ArtistConverter {
    @TypeConverter
    fun fromArtist(artist: ArtistEntity): String {
        return JSONObject().apply {
            put("id", artist.id)
            put("name", artist.name)
            put("link", artist.link)
            put("cover", artist.cover)
        }.toString()
    }

    @TypeConverter
    fun toArtist(artist: String): ArtistEntity {
        val json = JSONObject(artist)
        return ArtistEntity(
            json.getLong("id"),
            json.getString("name"),
            json.getString("link"),
            json.getString("cover")
        )
    }
}
