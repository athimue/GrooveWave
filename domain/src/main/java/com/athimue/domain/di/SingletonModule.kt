package com.athimue.domain.di

import com.athimue.domain.usecase.addFavoriteAlbum.*
import com.athimue.domain.usecase.addFavoriteArtist.*
import com.athimue.domain.usecase.addFavoriteTrack.*
import com.athimue.domain.usecase.addPlaylist.AddPlaylistUseCase
import com.athimue.domain.usecase.addPlaylist.AddPlaylistUseCaseImpl
import com.athimue.domain.usecase.addPlaylistTrack.AddPlaylistTrackUseCase
import com.athimue.domain.usecase.addPlaylistTrack.AddPlaylistTrackUseCaseImpl
import com.athimue.domain.usecase.deletePlaylist.DeletePlaylistUseCase
import com.athimue.domain.usecase.deletePlaylist.DeletePlaylistUseCaseImpl
import com.athimue.domain.usecase.deletePlaylistTrack.*
import com.athimue.domain.usecase.getAlbumSearch.*
import com.athimue.domain.usecase.getArtistSearch.*
import com.athimue.domain.usecase.getFavoriteAlbums.*
import com.athimue.domain.usecase.getFavoriteArtists.*
import com.athimue.domain.usecase.getFavoriteTracks.*
import com.athimue.domain.usecase.getPlaylistInfo.*
import com.athimue.domain.usecase.getPlaylists.*
import com.athimue.domain.usecase.getPopularAlbums.*
import com.athimue.domain.usecase.getPopularArtists.*
import com.athimue.domain.usecase.getPopularTracks.*
import com.athimue.domain.usecase.getTrackInfo.*
import com.athimue.domain.usecase.getTrackSearch.*
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
    abstract fun provideGetPlaylistsUseCase(getPlaylistUseCase: GetPlaylistUseCaseImpl): GetPlaylistsUseCase

    @Binds
    abstract fun provideGetTrackUseCase(getTrackInfoUseCase: GetTrackInfoUseCaseImpl): GetTrackInfoUseCase

    @Binds
    abstract fun provideGetFavoriteAlbumsUseCase(getFavoriteAlbumsUseCase: GetFavoriteAlbumsUseCaseImpl): GetFavoriteAlbumsUseCase

    @Binds
    abstract fun provideGetFavoriteArtistsUseCase(getFavoriteArtistsUseCase: GetFavoriteArtistsUseCaseImpl): GetFavoriteArtistsUseCase

    @Binds
    abstract fun provideGetFavoriteTrackUseCase(getFavoriteTrackUseCase: GetFavoriteTracksUseCaseImpl): GetFavoriteTracksUseCase

    @Binds
    abstract fun provideDeletePlaylistTrackUseCase(deletePlaylistTrackUseCase: DeletePlaylistTrackUseCaseImpl): DeletePlaylistTrackUseCase

    @Binds
    abstract fun provideGetPlaylistInfoUseCase(getPlaylistInfoUseCase: GetPlaylistInfoUseCaseImpl): GetPlaylistInfoUseCase

    @Binds
    abstract fun provideAddFavoriteTrackUseCase(addFavoriteTrackUseCase: AddFavoriteTrackUseCaseImpl): AddFavoriteTrackUseCase

    @Binds
    abstract fun provideAddFavoriteAlbumUseCase(addFavoriteAlbumUseCase: AddFavoriteAlbumUseCaseImpl): AddFavoriteAlbumUseCase

    @Binds
    abstract fun provideAddPlaylistUseCase(addPlaylistUseCase: AddPlaylistUseCaseImpl): AddPlaylistUseCase

    @Binds
    abstract fun provideDeletePlaylistUseCase(deletePlaylistUseCase: DeletePlaylistUseCaseImpl): DeletePlaylistUseCase

    @Binds
    abstract fun provideAddPlaylistTrackUseCase(addPlaylistTrackUseCase: AddPlaylistTrackUseCaseImpl): AddPlaylistTrackUseCase

    @Binds
    abstract fun provideAddFavoriteArtistUseCase(addFavoriteArtistUseCase: AddFavoriteArtistUseCaseImpl): AddFavoriteArtistUseCase

}