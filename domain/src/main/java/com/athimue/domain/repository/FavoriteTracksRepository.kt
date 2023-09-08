package com.athimue.domain.repository

import com.athimue.domain.model.Artist

interface FavoriteTracksRepository {
    suspend fun getFavoriteArtists(): List<Artist>
}