package com.athimue.data.di

import com.athimue.data.repository.*
import com.athimue.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class SingletonModule {

    @Binds
    abstract fun providePopularRepository(popularRepositoryImpl: PopularRepositoryImpl): PopularRepository

    @Binds
    abstract fun provideSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository

    @Binds
    abstract fun providePlaylistRepository(playlistRepositoryImpl: PlaylistRepositoryImpl): PlaylistRepository

    @Binds
    abstract fun provideFavoriteArtistsRepository(favoriteArtistsRepository: FavoriteArtistsRepositoryImpl): FavoriteArtistsRepository

    @Binds
    abstract fun provideFavoriteTracksRepository(favoriteTracksRepository: FavoriteTracksRepositoryImpl): FavoriteTracksRepository

    @Binds
    abstract fun provideFavoriteAlbumsRepository(favoriteAlbumsRepository: FavoriteAlbumsRepositoryImpl): FavoriteAlbumsRepository
}
