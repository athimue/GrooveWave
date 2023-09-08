package com.athimue.domain.repository

import com.athimue.domain.model.Artist

interface FavoriteArtistsRepository {
    suspend fun getFavoriteArtists(): List<Artist>
}