package com.athimue.data.repository

import com.athimue.domain.model.Artist
import com.athimue.domain.repository.FavoriteArtistsRepository
import javax.inject.Inject

class FavoriteArtistsRepositoryImpl @Inject constructor() : FavoriteArtistsRepository {
    override suspend fun getFavoriteArtists(): List<Artist> {
        TODO("Not yet implemented")
    }
}