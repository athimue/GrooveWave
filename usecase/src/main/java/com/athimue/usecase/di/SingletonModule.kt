package com.athimue.usecase.di

import com.athimue.domain.usecase.*
import com.athimue.usecase.implementation.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Module to inject/provide use cases.
 */
@InstallIn(SingletonComponent::class)
@Module
abstract class SingletonModule {

    @Binds
    abstract fun provideGetPopularTracksUseCase(getPopularTracksUseCaseImpl: GetPopularTracksUseCaseImpl): GetPopularTracksUseCaseInputOutput

    @Binds
    abstract fun provideGetPopularArtistsUseCase(getPopularArtistsUseCaseImpl: GetPopularArtistsUseCaseImpl): GetPopularArtistsUseCase

    @Binds
    abstract fun provideGetPopularAlbumsUseCase(getPopularAlbumsUseCaseImpl: GetPopularAlbumsUseCaseImpl): GetPopularAlbumsUseCase

    @Binds
    abstract fun provideGetTrackSearchUseCase(getTrackSearchUseCase: GetTrackSearchUseCaseImpl): GetTrackSearchUseCase

    @Binds
    abstract fun provideGetAlbumSearchUseCase(getAlbumSearchUseCase: GetAlbumSearchUseCaseImpl): GetAlbumSearchUseCase

    @Binds
    abstract fun provideGetArtistSearchUseCase(getArtistSearchUseCase: GetArtistSearchUseCaseImpl): GetArtistSearchUseCase

    @Binds
    abstract fun provideGetPlaylistsUseCase(getPlaylistUseCase: GetPlaylistUseCaseImpl): GetPlaylistUseCase

    @Binds
    abstract fun provideGetTrackUseCase(getTrackInfoUseCase: GetTrackInfoUseCaseImpl): GetTrackInfoUseCase
}
