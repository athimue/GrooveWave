package com.athimue.domain.repository

import com.athimue.domain.model.Album

interface FavoriteAlbumsRepository {
    suspend fun getFavoriteAlbums(): List<Album>
}