package com.athimue.usecase.implementation

import com.athimue.domain.model.Playlist
import com.athimue.domain.repository.PlaylistRepository
import com.athimue.domain.usecase.GetPlaylistUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlaylistUseCaseImpl @Inject constructor(
    private val playlistRepository: PlaylistRepository
) : GetPlaylistUseCase {

    override suspend fun invoke(): Flow<List<Playlist>> =
        playlistRepository.getPlaylists()
}