package com.athimue.data.repository

import com.athimue.domain.model.Artist
import com.athimue.domain.repository.FavoriteTracksRepository
import javax.inject.Inject

class FavoriteTracksRepositoryImpl @Inject constructor() : FavoriteTracksRepository {

    override suspend fun getFavoriteArtists(): List<Artist> {
        TODO("Not yet implemented")
    }
}