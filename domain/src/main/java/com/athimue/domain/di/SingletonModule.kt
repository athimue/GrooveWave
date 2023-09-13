package com.athimue.domain.di

import com.athimue.domain.usecase.addfavoritetrack.*
import com.athimue.domain.usecase.getalbumsearch.*
import com.athimue.domain.usecase.getartistsearch.*
import com.athimue.domain.usecase.getfavoritealbums.*
import com.athimue.domain.usecase.getfavoriteartists.*
import com.athimue.domain.usecase.getfavoritetracks.*
import com.athimue.domain.usecase.getplaylist.*
import com.athimue.domain.usecase.getpopularalbums.*
import com.athimue.domain.usecase.getpopularartists.*
import com.athimue.domain.usecase.getpopulartracks.*
import com.athimue.domain.usecase.gettrackinfo.*
import com.athimue.domain.usecase.gettracksearch.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class SingletonModule {

    @Binds
    abstract fun provideGetPopularTracksUseCase(getPopularTracksUseCaseImpl: GetPopularTracksUseCaseImpl): GetPopularTracksUseCase

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

    @Binds
    abstract fun provideAddFavoriteTrackUseCase(addFavoriteTrackUseCase: AddFavoriteTrackUseCaseImpl): AddFavoriteTrackUseCase

    @Binds
    abstract fun provideGetFavoriteAlbumsUseCase(getFavoriteAlbumsUseCase: GetFavoriteAlbumsUseCaseImpl): GetFavoriteAlbumsUseCase

    @Binds
    abstract fun provideGetFavoriteArtistsUseCase(getFavoriteArtistsUseCase: GetFavoriteArtistsUseCaseImpl): GetFavoriteArtistsUseCase

    @Binds
    abstract fun provideGetFavoriteTrackUseCase(getFavoriteTrackUseCase: GetFavoriteTracksUseCaseImpl): GetFavoriteTracksUseCase

}